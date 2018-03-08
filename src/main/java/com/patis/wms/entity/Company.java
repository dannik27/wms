package com.patis.wms.entity;





import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data @NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_person")
    private Person contactPerson;

    private String name;
    private String description;
    private String inn;
    private String kpp;
    private String email;
    private String phone;


}
