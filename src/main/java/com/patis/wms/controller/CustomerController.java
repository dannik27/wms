package com.patis.wms.controller;


import com.patis.wms.entity.Company;
import com.patis.wms.entity.Customer;
import com.patis.wms.service.CompanyService;
import com.patis.wms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    ResponseEntity<List<Customer>> findAll(){

        List<Customer> result = customerService.findAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    void save(@RequestBody Customer customer){

        if(true){
            customerService.save(customer);
        }

    }

    @DeleteMapping("/")
    void delete(@RequestBody Customer customer){

        if(true){
            customerService.remove(customer);
        }

    }

}
