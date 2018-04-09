package com.patis.wms.dto.create;

import com.patis.wms.dto.CompanyDTO;
import com.patis.wms.entity.TransportCompany;
import com.patis.wms.service.CompanyService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class TransportCompanyCreateDTO {

    private long id;
    private long id_company;



    public TransportCompany toEntity(CompanyService companyService){
        TransportCompany transportCompany = new TransportCompany();
        transportCompany.setId(id);
        transportCompany.setCompany(companyService.findOne(id_company));
        return transportCompany;
    }

}
