package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor
public class Transportation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_task_in")
    private Task taskIn;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_task_out")
    private Task taskOut;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_waybill")
    private Waybill waybill;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_packing_list")
    private PackingList packingList;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_request")
    private Request request;

    private LocalDate date;
    private float grossWeight;

}
