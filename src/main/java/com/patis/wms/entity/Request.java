package com.patis.wms.entity;





import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data @NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="request" )
    private List<RequestItem> requestItems;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_worker")
    private Worker author;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_customer")
    private Customer customer;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_storehouse_from")
    private Storehouse storehouseFrom;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_storehouse_to")
    private Storehouse storehouseTo;


    private LocalDate dateBegin;

}
