package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

@Entity
@Data @NoArgsConstructor
public class Transportation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_waybill")
    private Waybill waybill;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_packing_list")
    private PackingList packingList;

    @ManyToOne( cascade = {CascadeType.MERGE} )
    @JoinColumn(name="id_request")
    private Request request;

    private LocalDateTime dateShipped;
    private LocalDateTime dateReceived;
    private float grossWeight;




}
