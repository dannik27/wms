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

    @Data @NoArgsConstructor
    public class StorehouseDTO{
        private long id;
        private String name;
        private String address;

        public StorehouseDTO(Storehouse storehouse){
            id = storehouse.getId();
            name = storehouse.getName();
            address = storehouse.getAddress();
        }
    }

    @Data @NoArgsConstructor
    public class CustomerDTO{
        private long id;
        private CompanyDTO company;

        public CustomerDTO(Customer customer){
            id = customer.getId();
            company = new CompanyDTO(customer.getCompany());
        }
    }

    @Data @NoArgsConstructor
    private class CompanyDTO{
        private long id;
        private String name;

        public CompanyDTO(Company company){
            id = company.getId();
            name = company.getName();
        }
    }

    @Data
    @NoArgsConstructor
    public class RequestItemDTO {

        private long id;
        private int count;
        private Product product;

        public RequestItemDTO(RequestItem requestItem){
            id = requestItem.getId();
            count = requestItem.getCount();
            product = requestItem.getProduct();
        }
    }


    public RequestDTO(Request request){
        id = request.getId();
        requestItems = request.getRequestItems().stream().map(RequestItemDTO::new).collect(Collectors.toList());
        author = new WorkerDTO(request.getAuthor());
        storehouseFrom = new StorehouseDTO(request.getStorehouseFrom());
        storehouseTo = new StorehouseDTO(request.getStorehouseTo());
        dateBegin = request.getDateBegin();
        customer = new CustomerDTO(request.getCustomer());
    }
}
