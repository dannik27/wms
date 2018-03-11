package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Storehouse {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="storehouse" )
    private List<StorehouseCell> storehouseCells;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_customer")
    private Customer customer;

    private String name;
}
