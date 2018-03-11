package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class StorehouseCell {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_storehouse")
    private Storehouse storehouse;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="storehouseCell" )
    private List<Distribution> distributions;

    private String name;
    private float capacity;

}
