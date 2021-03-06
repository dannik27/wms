package com.patis.wms.entity;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_company")
    private Company company;

    @Expose
    @OneToMany( cascade = CascadeType.ALL, mappedBy="customer" )
    private List<Request> requests;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="customer" )
    private List<Storehouse> storehouses;




}
