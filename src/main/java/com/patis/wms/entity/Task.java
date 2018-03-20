package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_transportation")
    private Transportation transportation;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_worker")
    private Worker worker;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="task" )
    private List<TaskItem> taskItems;

    @Enumerated(EnumType.ORDINAL)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.ORDINAL)
    private OperationType operationType;

    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;

}
