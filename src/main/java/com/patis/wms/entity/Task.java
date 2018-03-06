package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;

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

}
