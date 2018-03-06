package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_task")
    private Task task;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_transportation")
    private Transportation transportation;

}
