package com.patis.wms.converter.dto;

import com.patis.wms.dto.RequestDTOI;
import com.patis.wms.entity.Request;
import com.patis.wms.service.CompanyService;
import com.patis.wms.service.CustomerService;
import com.patis.wms.service.StorehouseService;
import com.patis.wms.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoConverter {

    @Autowired
    WorkerService workerService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CompanyService companyService;
    @Autowired
    StorehouseService storehouseService;

    public Request request(RequestDTOI requestDTOI){

        Request request = new Request();
        request.setId(requestDTOI.getId());
        request.setDateBegin(requestDTOI.getDateBegin());
        request.setAuthor(workerService.findOne(requestDTOI.getId_author()));
        request.setCustomer(customerService.findOne(requestDTOI.getId_customer()));
        request.setStorehouseFrom(storehouseService.findOne(requestDTOI.getId_storehouse_from()));
        request.setStorehouseTo(storehouseService.findOne(requestDTOI.getId_storehouse_to()));
        return request;
    }

}
