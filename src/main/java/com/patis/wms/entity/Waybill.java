package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class Waybill {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_transportation")
    private Transportation transportation;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_transport_company")
    private TransportCompany transportCompany;

}
