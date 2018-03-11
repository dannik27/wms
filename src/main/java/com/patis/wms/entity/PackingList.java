package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class PackingList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_transportation")
    private Transportation transportation;

}
