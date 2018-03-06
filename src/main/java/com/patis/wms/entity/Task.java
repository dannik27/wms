package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_task_type")
    private TaskType taskType;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_worker")
    private Worker worker;

    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;

}
