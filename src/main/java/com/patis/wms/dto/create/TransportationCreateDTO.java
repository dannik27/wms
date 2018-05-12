package com.patis.wms.dto.create;

import com.patis.wms.dto.PackingListDTO;
import com.patis.wms.entity.Transportation;
import com.patis.wms.service.RequestService;
import com.patis.wms.service.TransportCompanyService;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TransportationCreateDTO implements Serializable {

    private long id;
    private float grossWeight;
//    private LocalDateTime dateShipped;
//    private LocalDateTime dateReceived;
    private long id_request;

    private WaybillCreateDTO waybill;
    private PackingListDTO packingList;


    public Transportation toEntity(RequestService requestService, TransportCompanyService transportCompanyService){
        Transportation transportation = new Transportation();
        transportation.setId(id);
        transportation.setGrossWeight(grossWeight);
//        transportation.setDateShipped(dateShipped);
//        transportation.setDateReceived(dateReceived);
        transportation.setRequest(requestService.findOne(id_request));
        if(waybill != null) transportation.setWaybill(waybill.toEntity(transportCompanyService));
        if(packingList != null) transportation.setPackingList(packingList.toEntity());
        return transportation;
    }


}
