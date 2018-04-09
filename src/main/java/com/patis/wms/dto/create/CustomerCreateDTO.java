package com.patis.wms.dto.create;


import com.patis.wms.dto.CompanyDTO;
import com.patis.wms.entity.Customer;
import com.patis.wms.service.CompanyService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class CustomerCreateDTO {


    private long id;
    private long id_company;

    public Customer toEntity(CompanyService companyService){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCompany(companyService.findOne(id_company));
        return customer;
    }
}
