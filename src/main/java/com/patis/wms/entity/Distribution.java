package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Distribution {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_storehouse_cell")
    private StorehouseCell storehouseCell;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_task_item")
    private TaskItem taskItem;

    private int count;


}
