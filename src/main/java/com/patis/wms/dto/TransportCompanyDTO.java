package com.patis.wms.dto;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Customer;
import com.patis.wms.entity.TransportCompany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TransportCompanyDTO {

    private long id;
    private Company company;


    public TransportCompanyDTO(TransportCompany transportCompany){
        id = transportCompany.getId();
        company = transportCompany.getCompany();
    }

    public TransportCompany toEntity(){
        TransportCompany transportCompany = new TransportCompany();
        transportCompany.setId(id);
        transportCompany.setCompany(company);
        return transportCompany;
    }

}
