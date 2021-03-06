package com.patis.wms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class TaskItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_task")
    private Task task;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_product")
    private Product product;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="taskItem" )
    private List<Distribution> distributions;

    private int count;



    public TaskItem(RequestItem requestItem){
        this.product = requestItem.getProduct();
        this.count = requestItem.getCount();
    }

    public List<Distribution> getDistributions() {
        if (distributions == null) distributions = new ArrayList<>();
        return distributions;
    }
}
