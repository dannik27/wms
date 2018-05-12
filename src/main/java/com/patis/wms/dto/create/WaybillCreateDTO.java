package com.patis.wms.dto.create;

import com.patis.wms.entity.Waybill;
import com.patis.wms.service.TransportCompanyService;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class WaybillCreateDTO implements Serializable{

    private long id;
    private String info;
    private long transportCompanyId;


    public Waybill toEntity(TransportCompanyService transportCompanyService){
        Waybill waybill = new Waybill();
        waybill.setInfo(info);
        waybill.setTransportCompany(transportCompanyService.findOne(transportCompanyId));
        return waybill;
    }

}
