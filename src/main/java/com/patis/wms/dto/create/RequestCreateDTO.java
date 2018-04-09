package com.patis.wms.dto.create;

import com.patis.wms.dto.CustomerDTO;
import com.patis.wms.dto.StorehouseDTO;
import com.patis.wms.dto.WorkerDTO;
import com.patis.wms.entity.Request;
import com.patis.wms.service.CustomerService;
import com.patis.wms.service.StorehouseService;
import com.patis.wms.service.WorkerService;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RequestCreateDTO {

    private long id;
    private long id_author;
    private long id_storehouse_from;
    private long id_storehouse_to;
    private LocalDate dateBegin;
    private long id_customer;



    public Request toEntity(WorkerService workerService, StorehouseService storehouseService, CustomerService customerService){
        Request request = new Request();
        request.setId(id);
        request.setAuthor(workerService.findOne(id_author));
        request.setStorehouseFrom(storehouseService.findOne(id_storehouse_from));
        request.setStorehouseTo(storehouseService.findOne(id_storehouse_to));
        request.setDateBegin(dateBegin);
        request.setCustomer(customerService.findOne(id_customer));
        return request;
    }
}
