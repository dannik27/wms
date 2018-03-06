package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_company")
    private Company company;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="customer" )
    private List<Transportation> transportation;

}
