package com.patis.wms.service;

import com.patis.wms.StorehouseException;
import com.patis.wms.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskManagerService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TransportationService transportationService;

    @Autowired
    private StorehouseService storehouseService;

    private int countToGet;

    public void transportationStart(Transportation transportation, LocalDateTime dataShipped) throws StorehouseException {


        Request request = transportation.getRequest();

        if((request.getOperationType() == OperationType.OUT)||(request.getOperationType() == OperationType.IN_OUT)){


            Worker freeWorker = request.getStorehouseFrom().getWorkers().stream().min(Comparator.comparingInt(Worker::getCurrentTaskCount)).get();

            Task task = new Task();
            task.setOperationType(OperationType.OUT);
            task.setTaskStatus(TaskStatus.READY);
            task.setTimeBegin(LocalDateTime.now());
            task.setTimeEnd(dataShipped);
            task.setTransportation(transportation);
            task.setWorker(freeWorker);
            task.setTaskItems(new ArrayList<>());

            request.getRequestItems().stream()
                    .map(TaskItem::new)
                    .forEach(taskItem -> {
                        task.getTaskItems().add(taskItem);
                        taskItem.setTask(task);
                    });

            if( ! checkTaskImplementability(task) ){
                throw new StorehouseException("Недостаточно на складе");
            }else{

                for(TaskItem taskItem : task.getTaskItems()){

                    countToGet = taskItem.getCount();
                    request.getStorehouseFrom().getStorehouseCells().stream()
                            .filter(storehouseCell -> storehouseCell.getProduct() == taskItem.getProduct())
                            .forEach(storehouseCell -> {
                                int availableCount = (int)(storehouseCell.getBusy() / storehouseCell.getProduct().getVolume());

                                if(availableCount <= countToGet){
                                    taskItem.getDistributions().add(new Distribution(storehouseCell, taskItem,  (-1) * availableCount));
                                    countToGet -= availableCount;
                                    storehouseCell.setProduct(null);

                                }else if(countToGet != 0){
                                    taskItem.getDistributions().add(new Distribution(storehouseCell, taskItem, (-1) * countToGet));
                                    countToGet = 0;
                                }
                            });
                }

                storehouseService.save(request.getStorehouseFrom());

                taskService.save(task);
                transportation.setDateShipped(dataShipped);
                transportationService.save(transportation);
            }



        }else{
            transportation.setDateShipped(dataShipped);
            transportationService.save(transportation);
        }
    }

    public void transportationReceived(Transportation transportation, LocalDateTime dateReceived) throws StorehouseException {

        Request request = transportation.getRequest();

        if((transportation.getRequest().getOperationType() == OperationType.IN)||(transportation.getRequest().getOperationType() == OperationType.IN_OUT)){

            Worker freeWorker = request.getStorehouseTo().getWorkers().stream().min(Comparator.comparingInt(Worker::getCurrentTaskCount)).get();

            Task task = new Task();
            task.setOperationType(OperationType.IN);
            task.setTaskStatus(TaskStatus.READY);
            task.setTimeBegin(dateReceived);
            task.setTransportation(transportation);
            task.setWorker(freeWorker);
            task.setTaskItems(new ArrayList<>());

            request.getRequestItems().stream()
                    .map(TaskItem::new)
                    .forEach(taskItem -> {
                        task.getTaskItems().add(taskItem);
                        taskItem.setTask(task);
                    });

            fillStorehouse(task).stream()
                    .filter(cellContainer -> cellContainer.countNew != 0)
                    .forEach(cellContainer -> {
                        cellContainer.taskItem.getDistributions().add(new Distribution(cellContainer.storehouseCell, cellContainer.taskItem, cellContainer.countNew));
                        cellContainer.storehouseCell.setProduct(cellContainer.taskItem.getProduct());
                    });

            storehouseService.save(request.getStorehouseTo());
            taskService.save(task);
            transportation.setDateReceived(dateReceived);
            transportationService.save(transportation);
        }else{
            transportation.setDateReceived(dateReceived);
            transportationService.save(transportation);
        }
    }

    private int productCountInStorehouse(Storehouse storehouse, Product product){

        return storehouse.getStorehouseCells().stream().flatMap(storehouseCell -> storehouseCell.getDistributions().stream())
                .filter(Distribution::isDone)
                .filter(distribution -> distribution.getTaskItem().getProduct() == product)
                .map(Distribution::getCount)
                .reduce((a,b) -> a + b).orElse(0);

    }

    private boolean checkTaskImplementability(Task task){

        return task.getTaskItems().stream()
                .noneMatch(taskItem -> taskItem.getCount() > productCountInStorehouse(task.getTransportation().getRequest().getStorehouseFrom(), taskItem.getProduct()));
    }

    public List<CellContainer> fillStorehouse(Task inputTask) throws StorehouseException {
        Storehouse storehouse = inputTask.getTransportation().getRequest().getStorehouseTo();

        Comparator<Container> comparator = Comparator.comparingInt(o -> o.count);


        List<CellContainer> cells = storehouse.getStorehouseCells().stream()
                .filter(cell -> (cell.getProduct() == null))
                .map(CellContainer::new).collect(Collectors.toList());

        List<TaskContainer> tasks = inputTask.getTaskItems().stream()
                .map(TaskContainer::new).collect(Collectors.toList());

        cells.sort(comparator);
        tasks.sort(comparator.reversed());

        while (tasks.size() > 0){

            TaskContainer task = tasks.get(0);
            CellContainer lastCell = cells.get(cells.size() - 1);
            CellContainer curCell;

            curCell = cells.stream()
                    .peek(cell -> cell.count = (int) (cell.storehouseCell.getCapacity() / task.taskItem.getProduct().getVolume()))
                    .filter(cell -> cell.count >= task.count)
                    .filter(cell -> cell.countNew == 0)
                    .findFirst()
                    .orElse(lastCell);

            if((curCell == lastCell)&&(curCell.countNew != 0)){ // В последней (самой большой) ячейке не хватает места
                throw new StorehouseException("Не хватает места");
            }

            if(curCell.count < task.count){



                curCell.countNew = curCell.count;
                curCell.count = 0;
                curCell.taskItem = task.taskItem;
                tasks.remove(task);
                tasks.add(new TaskContainer(task.taskItem, task.count - curCell.countNew));
                tasks.sort(comparator.reversed());

            }else{

                curCell.countNew = task.count;
                curCell.count -= task.count;
                curCell.taskItem = task.taskItem;
                tasks.remove(task);
            }

        }

        return cells;
    }


    class Container{
        int count;
    }
    public class TaskContainer extends Container{
        TaskItem taskItem;

        TaskContainer(TaskItem taskItem, int count) {
            this.taskItem = taskItem;
            this.count = count;
        }
        TaskContainer(TaskItem taskItem) {
            this.taskItem = taskItem;
            this.count = taskItem.getCount();
        }

    }
    public class CellContainer extends Container{
        StorehouseCell storehouseCell;
        TaskItem taskItem;
        int countNew;

        public CellContainer(StorehouseCell storehouseCell, int count, TaskItem taskItem) {
            this.storehouseCell = storehouseCell;
            this.count = count;
            this.taskItem = taskItem;
        }

        CellContainer(StorehouseCell storehouseCell){
            this.storehouseCell = storehouseCell;
            if(storehouseCell.getProduct() != null){
                this.count = (int)((storehouseCell.getCapacity() - storehouseCell.getBusy()) / storehouseCell.getProduct().getVolume());
            }else{
                count = (int) storehouseCell.getCapacity();
            }
            taskItem = null;

        }
    }

}
