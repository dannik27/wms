package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor
public class Transportation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_acceptence")
    private Acceptence acceptence;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_shipment")
    private Shipment shipment;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_customer")
    private Customer customer;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_waybill")
    private Waybill waybill;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_packing_list")
    private PackingList packingList;

    private LocalDate date;
    private float grossWeight;

}
