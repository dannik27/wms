package com.patis.wms.dto;


import com.patis.wms.entity.OperationType;
import com.patis.wms.entity.Task;
import com.patis.wms.entity.TaskStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TaskDTO {

    private long id;
    private String taskStatus;
    private OperationType operationType;
    private List<DistributionDTO> distributions;
    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;
    private WorkerDTO worker;
    private String customerName;
    private long transportationId;
    private String storehouseName;

    public TaskDTO(Task task){
        id = task.getId();
        taskStatus = task.getTaskStatus().name();
        operationType = task.getOperationType();

        distributions = task.getTaskItems().stream()
                .flatMap(t -> t.getDistributions().stream())
                .map(DistributionDTO::new).collect(Collectors.toList());

        timeBegin = task.getTimeBegin();
        timeEnd = task.getTimeEnd();
        worker = new WorkerDTO(task.getWorker());

        if(task.getTransportation().getRequest().getCustomer() != null){
            customerName = task.getTransportation().getRequest().getCustomer().getCompany().getName();
        }else{
            customerName = "Перенос";
        }

        transportationId = task.getTransportation().getId();

        if(operationType == OperationType.IN){
            storehouseName = task.getTransportation().getRequest().getStorehouseTo().getName();
        }else{
            storehouseName = task.getTransportation().getRequest().getStorehouseFrom().getName();
        }
    }

}
