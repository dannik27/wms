package com.patis.wms.controller;


import com.patis.wms.entity.Company;
import com.patis.wms.entity.Person;
import com.patis.wms.service.CompanyService;
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

    @GetMapping({"/", " "})
    ResponseEntity<List<Company>> findAll(){

        List<Company> result = companyService.findAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping({"/", " "})
    void save(@RequestBody Company company){

        if(true){

            companyService.save(company);

        }

    }

}
