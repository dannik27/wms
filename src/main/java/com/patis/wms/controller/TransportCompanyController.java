package com.patis.wms.controller;


import com.patis.wms.dto.CompanyDTO;
import com.patis.wms.dto.TransportCompanyDTO;
import com.patis.wms.dto.create.CompanyCreateDTO;
import com.patis.wms.dto.create.TransportCompanyCreateDTO;
import com.patis.wms.entity.Company;
import com.patis.wms.entity.TransportCompany;
import com.patis.wms.service.CompanyService;
import com.patis.wms.service.PersonService;
import com.patis.wms.service.TransportCompanyService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transportCompany")
public class TransportCompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    TransportCompanyService transportCompanyService;


    @GetMapping("/")
    ResponseEntity<List<TransportCompanyDTO>> findAll(){

        List<TransportCompanyDTO> result = transportCompanyService.findAll().stream().map(TransportCompanyDTO::new).collect(
            Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    long save(@RequestBody TransportCompanyCreateDTO transportCompanyCreateDTO){

        TransportCompany transportCompany = transportCompanyService.save(transportCompanyCreateDTO.toEntity(companyService));
        return transportCompany.getId();

    }

    @DeleteMapping("/{id_transport_company}")
    void delete(@PathVariable long id_transport_company){

        if(true){
            transportCompanyService.remove(id_transport_company);
        }

    }

}
