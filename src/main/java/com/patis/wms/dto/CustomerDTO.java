package com.patis.wms.dto;


import com.patis.wms.entity.Company;
import com.patis.wms.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class CustomerDTO {


    private long id;
    private CompanyDTO company;


    public CustomerDTO(Customer customer){
        id = customer.getId();
        company = new CompanyDTO(customer.getCompany());
    }

    public Customer toEntity(){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCompany(company.toEntity());
        return customer;
    }
}
