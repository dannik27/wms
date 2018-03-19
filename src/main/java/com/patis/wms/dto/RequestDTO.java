package com.patis.wms.dto;

import com.patis.wms.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.Store;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data @NoArgsConstructor
public class RequestDTO {

    private long id;
    private List<RequestItemDTO> requestItems;
    private WorkerDTO author;
    private StorehouseDTO storehouseFrom;
    private StorehouseDTO storehouseTo;
    private LocalDate dateBegin;
    private CustomerDTO customer;


    public RequestDTO(Request request){
        id = request.getId();
        requestItems = request.getRequestItems().stream().map(RequestItemDTO::new).collect(Collectors.toList());
        author = new WorkerDTO(request.getAuthor());
        storehouseFrom = new StorehouseDTO(request.getStorehouseFrom());
        storehouseTo = new StorehouseDTO(request.getStorehouseTo());
        dateBegin = request.getDateBegin();
        customer = new CustomerDTO(request.getCustomer());
    }

    public Request toEntity(){
        Request request = new Request();
        request.setId(id);
        request.setRequestItems(requestItems.stream().map(RequestItemDTO::toEntity).collect(Collectors.toList()));
        request.setAuthor(author.toEntity());
        if(storehouseFrom != null) {request.setStorehouseFrom(storehouseFrom.toEntity());}
        if(storehouseTo != null) {request.setStorehouseTo(storehouseTo.toEntity());}
        request.setDateBegin(dateBegin);
        if(customer != null) {request.setCustomer(customer.toEntity());}
        return request;
    }
}
