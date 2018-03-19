package com.patis.wms.dto;

import com.patis.wms.entity.PackingList;
import com.patis.wms.entity.Task;
import com.patis.wms.entity.Transportation;
import com.patis.wms.entity.Waybill;
import com.patis.wms.service.TaskService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Data @NoArgsConstructor
public class TransportationDTO {

    private long id;
    private float grossWeight;
    private LocalDate date;
    private RequestDTO request;
    private WaybillDTO waybill;
    private PackingListDTO packingList;

    private long id_task_in;
    private long id_task_out;

    public TransportationDTO(Transportation transportation){
        id = transportation.getId();
        grossWeight = transportation.getGrossWeight();
        date = transportation.getDate();
        request = new RequestDTO(transportation.getRequest());
        waybill = new WaybillDTO(transportation.getWaybill());
        packingList = new PackingListDTO(transportation.getPackingList());
        if(transportation.getTaskIn() != null){
            id_task_in = transportation.getTaskIn().getId();
        }
        if(transportation.getTaskOut() != null){
            id_task_out = transportation.getTaskOut().getId();
        }
    }


    public Transportation toEntity(TaskService taskService){
        Transportation transportation = new Transportation();
        transportation.setId(id);
        transportation.setGrossWeight(grossWeight);
        transportation.setDate(date);
        transportation.setRequest(request.toEntity());
        transportation.setWaybill(waybill.toEntity());
        transportation.setPackingList(packingList.toEntity());
        Task tempTask = taskService.findOne(id_task_in);
        if(tempTask != null){ transportation.setTaskIn(tempTask); }
        tempTask = taskService.findOne(id_task_out);
        if(tempTask != null){ transportation.setTaskOut(tempTask); }
        return transportation;
    }


}
