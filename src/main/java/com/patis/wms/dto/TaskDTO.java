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
    private TaskStatus taskStatus;
    private OperationType operationType;
    private List<DistributionDTO> distributions;
    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;
    private WorkerDTO worker;
    private String customerName;

    public TaskDTO(Task task){
        id = task.getId();
        taskStatus = task.getTaskStatus();
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
    }

}
