package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class Distribution {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_storehouse_cell")
    private StorehouseCell storehouseCell;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_task_item")
    private TaskItem taskItem;

    private int count;


}
