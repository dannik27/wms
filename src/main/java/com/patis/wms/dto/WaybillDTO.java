package com.patis.wms.dto;

import com.patis.wms.entity.Waybill;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor
public class WaybillDTO implements Serializable {

    private long id;
    private String info;
    private TransportCompanyDTO transportCompany;


    public WaybillDTO(Waybill waybill){
        id = waybill.getId();
        info = waybill.getInfo();
        if(waybill.getTransportCompany() != null){
            transportCompany = new TransportCompanyDTO(waybill.getTransportCompany());
        }

    }

    public Waybill toEntity(){
        Waybill waybill = new Waybill();
        waybill.setId(id);
        waybill.setInfo(info);
        if(transportCompany != null){
            waybill.setTransportCompany(transportCompany.toEntity());
        }
        return waybill;
    }

}
