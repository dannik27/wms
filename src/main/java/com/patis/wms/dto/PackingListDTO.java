package com.patis.wms.dto;

import com.patis.wms.entity.PackingList;
import com.patis.wms.entity.Waybill;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor
public class PackingListDTO implements Serializable {

    private long id;
    private String info;


    public PackingListDTO(PackingList packingList){
        id = packingList.getId();
        info = packingList.getInfo();
    }

    public PackingList toEntity(){
        PackingList packingList = new PackingList();
        packingList.setId(id);
        packingList.setInfo(info);
        return packingList;
    }

}
