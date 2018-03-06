package com.patis.wms.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class TransportCompany {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="id_company")
    private Company company;


}
