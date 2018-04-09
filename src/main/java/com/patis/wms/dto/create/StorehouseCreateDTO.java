package com.patis.wms.dto.create;

import com.patis.wms.entity.Storehouse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StorehouseCreateDTO {

    private long id;
    private String name;
    private String address;

    public Storehouse toEntity(){
        Storehouse storehouse = new Storehouse();
        storehouse.setId(id);
        storehouse.setName(name);
        storehouse.setAddress(address);
        return storehouse;
    }





}
