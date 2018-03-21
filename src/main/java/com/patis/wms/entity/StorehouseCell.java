package com.patis.wms.entity;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class StorehouseCell {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_storehouse")
    private Storehouse storehouse;


    @OneToMany( cascade = CascadeType.ALL, mappedBy="storehouseCell" )
    private List<Distribution> distributions;

    private String name;
    private float capacity;


    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_product")
    private Product product;



    public float getBusy(){
        if(product != null) {
            return distributions.stream()
                    .filter(distribution -> distribution.getTaskItem().getProduct() == product)
                    .map(distribution -> distribution.getCount() * product.getVolume())
                    .reduce((a, b) -> a + b).orElse(0f);
        }
        return 0;
    }

}
