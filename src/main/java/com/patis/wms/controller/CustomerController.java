package com.patis.wms.controller;


import com.patis.wms.dto.CustomerDTO;
import com.patis.wms.entity.Company;
import com.patis.wms.entity.Customer;
import com.patis.wms.service.CompanyService;
import com.patis.wms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    ResponseEntity<List<CustomerDTO>> findAll(){

        List<CustomerDTO> result = customerService.findAll().stream().map(CustomerDTO::new).collect(Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    void save(@RequestBody CustomerDTO customer){

        if(true){
            customerService.save(customer.toEntity());
        }

    }

    @DeleteMapping("/{id_customer}/")
    void delete(@PathVariable("id_customer") long id_customer){

        if(true){
            customerService.remove(id_customer);
        }

    }

}
