package com.patis.wms.controller;


import com.patis.wms.dto.CompanyDTO;
import com.patis.wms.dto.create.CompanyCreateDTO;
import com.patis.wms.entity.Company;
import com.patis.wms.entity.Person;
import com.patis.wms.service.CompanyService;
import com.patis.wms.service.PersonService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    PersonService personService;

    @GetMapping("/")
    ResponseEntity<List<CompanyDTO>> findAll(){

        List<CompanyDTO> result = companyService.findAll().stream().map(CompanyDTO::new).collect(
            Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    long save(@RequestBody CompanyCreateDTO companyDTO){

        Company company = companyService.save(companyDTO.toEntity(personService));
        return company.getId();

    }

    @DeleteMapping("/{id_company}")
    void delete(@PathVariable long id_company){

        if(true){
            companyService.remove(id_company);
        }

    }

}
