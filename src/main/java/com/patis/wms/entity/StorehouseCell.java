package com.patis.wms.entity;

import com.google.gson.annotations.Expose;
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

    @Expose
    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_storehouse")
    private Storehouse storehouse;

    @Expose
    @OneToMany( cascade = CascadeType.ALL, mappedBy="storehouseCell" )
    private List<Distribution> distributions;

    private String name;
    private float capacity;

}
