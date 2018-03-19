package com.patis.wms.dto;

import com.patis.wms.entity.Waybill;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class WaybillDTO {

    private long id;
    private String info;
    private TransportCompanyDTO transportCompany;


    public WaybillDTO(Waybill waybill){
        id = waybill.getId();
        info = waybill.getInfo();
        transportCompany = new TransportCompanyDTO(waybill.getTransportCompany());
    }

    public Waybill toEntity(){
        Waybill waybill = new Waybill();
        waybill.setId(id);
        waybill.setInfo(info);
        waybill.setTransportCompany(transportCompany.toEntity());
        return waybill;
    }

}
