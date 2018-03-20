package com.patis.wms.dto;


import com.patis.wms.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data @NoArgsConstructor
public class TaskDTO {

    private long id;
    private TaskStatus taskStatus;
    private OperationType operationType;
    private List<DistributionDTO> distributions;
    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;

    public TaskDTO(Task task){
        id = task.getId();
        taskStatus = task.getTaskStatus();
        operationType = task.getOperationType();

        distributions = task.getTaskItems().stream()
                .flatMap(t -> t.getDistributions().stream())
                .map(DistributionDTO::new).collect(Collectors.toList());

        timeBegin = task.getTimeBegin();
        timeEnd = task.getTimeEnd();
    }

}
