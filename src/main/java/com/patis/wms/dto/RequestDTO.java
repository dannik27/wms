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
    private WorkerDTO author;
    private StorehouseDTO storehouseFrom;
    private StorehouseDTO storehouseTo;
    private LocalDate dateBegin;
    private CustomerDTO customer;
    private OperationType operationType;


    public RequestDTO(Request request){
        id = request.getId();
        author = new WorkerDTO(request.getAuthor());
        if(request.getStorehouseFrom()!=null){storehouseFrom = new StorehouseDTO(request.getStorehouseFrom());}
        if(request.getStorehouseTo()!=null){storehouseTo = new StorehouseDTO(request.getStorehouseTo());}
        dateBegin = request.getDateBegin();
        if(request.getCustomer()!=null){customer = new CustomerDTO(request.getCustomer());}
        operationType = request.getOperationType();
    }

    public Request toEntity(){
        Request request = new Request();
        request.setId(id);
        request.setAuthor(author.toEntity());
        if(storehouseFrom != null) {request.setStorehouseFrom(storehouseFrom.toEntity());}
        if(storehouseTo != null) {request.setStorehouseTo(storehouseTo.toEntity());}
        request.setDateBegin(dateBegin);
        if(customer != null) {request.setCustomer(customer.toEntity());}
        request.setOperationType(operationType);
        return request;
    }
}
