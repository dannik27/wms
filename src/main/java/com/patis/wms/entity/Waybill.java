package com.patis.wms.entity;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class Waybill {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Expose
    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_transportation")
    private Transportation transportation;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_transport_company")
    private TransportCompany transportCompany;


    private String info;

}
