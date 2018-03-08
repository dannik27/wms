package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class PackingListItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_packing_list")
    private PackingList packingList;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_product")
    private Product product;

    private int count;

}
