package com.patis.wms.dto;

import com.patis.wms.entity.Transportation;
import com.patis.wms.service.TaskService;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDateTime;

@Data @NoArgsConstructor
public class TransportationDTO {

    private long id;
    private float grossWeight;
    private LocalDateTime dateShipped;
    private LocalDateTime dateReceived;
    private RequestDTO request;
    private WaybillDTO waybill;
    private PackingListDTO packingList;

    private long id_task_in;
    private long id_task_out;

    public TransportationDTO(Transportation transportation){
        id = transportation.getId();
        grossWeight = transportation.getGrossWeight();
        dateShipped = transportation.getDateShipped();
        dateReceived = transportation.getDateReceived();
        request = new RequestDTO(transportation.getRequest());
        waybill = new WaybillDTO(transportation.getWaybill());
        packingList = new PackingListDTO(transportation.getPackingList());

    }


    public Transportation toEntity(TaskService taskService){
        Transportation transportation = new Transportation();
        transportation.setId(id);
        transportation.setGrossWeight(grossWeight);
        transportation.setDateShipped(dateShipped);
        transportation.setDateReceived(dateReceived);
        transportation.setRequest(request.toEntity());
        transportation.setWaybill(waybill.toEntity());
        transportation.setPackingList(packingList.toEntity());
        return transportation;
    }


}
