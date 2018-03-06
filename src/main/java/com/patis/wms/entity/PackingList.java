package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class PackingList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="packingList" )
    private List<PackingListItem> packingListItems;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_transportation")
    private Transportation transportation;

}
