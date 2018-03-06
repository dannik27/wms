package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Worker {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_person")
    private Person person;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="worker" )
    private List<Task> tasks;

}
