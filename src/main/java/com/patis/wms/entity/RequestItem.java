package com.patis.wms.entity;


import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data @NoArgsConstructor
public class RequestItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Expose
    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_request")
    private Request request;

    @ManyToOne( cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    @JoinColumn(name="id_product")
    private Product product;

    private int count;


}
