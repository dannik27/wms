package com.patis.wms.entity;





import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data @NoArgsConstructor
public class Acceptence {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_task")
    private Task task;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_transportation")
    private Transportation transportation;

}
