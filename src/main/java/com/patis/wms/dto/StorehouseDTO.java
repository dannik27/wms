package com.patis.wms.dto;

import com.patis.wms.entity.Product;
import com.patis.wms.entity.Storehouse;
import com.patis.wms.entity.StorehouseCell;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class StorehouseDTO {

    private long id;
    private String name;
    private String address;


    public StorehouseDTO(Storehouse storehouse){

        id = storehouse.getId();
        name = storehouse.getName();
        address = storehouse.getAddress();
    }

    public Storehouse toEntity(){
        Storehouse storehouse = new Storehouse();
        storehouse.setId(id);
        storehouse.setName(name);
        storehouse.setAddress(address);
        return storehouse;
    }





}
