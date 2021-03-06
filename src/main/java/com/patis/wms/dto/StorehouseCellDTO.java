package com.patis.wms.dto;

import com.patis.wms.entity.Product;
import com.patis.wms.entity.StorehouseCell;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StorehouseCellDTO{

    private long id;
    private String name;
    private float capacity;
    private float busy;
    private Product product;


    public StorehouseCellDTO(StorehouseCell storehouseCell){
        id = storehouseCell.getId();
        name = storehouseCell.getName();
        capacity = storehouseCell.getCapacity();
        product = storehouseCell.getProduct();
        busy = storehouseCell.getBusy();


    }

    public StorehouseCell toEntity(){
        StorehouseCell storehouseCell = new StorehouseCell();
        storehouseCell.setId(id);
        storehouseCell.setName(name);
        storehouseCell.setCapacity(capacity);
        return storehouseCell;
    }

}
