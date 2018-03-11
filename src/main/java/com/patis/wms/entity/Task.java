package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_task_type")
    private TaskType taskType;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_worker")
    private Worker worker;

    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;

}
