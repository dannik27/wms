package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class StorehouseCell {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_storehouse")
    private Storehouse storehouse;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="storehouseCell" )
    private List<Distribution> distributions;

    private String name;
    private float capacity;

}
