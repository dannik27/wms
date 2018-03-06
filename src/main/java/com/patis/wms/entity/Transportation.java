package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Transportation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_acceptence")
    private Acceptence acceptence;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_shipment")
    private Shipment shipment;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_customer")
    private Customer customer;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_waybill")
    private Waybill waybill;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_packing_list")
    private PackingList packingList;

}
