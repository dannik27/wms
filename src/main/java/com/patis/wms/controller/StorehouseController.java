package com.patis.wms.controller;


import com.patis.wms.entity.Customer;
import com.patis.wms.entity.Storehouse;
import com.patis.wms.service.CustomerService;
import com.patis.wms.service.StorehouseService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("storehouse")
public class StorehouseController {

    @Autowired
    StorehouseService storehouseService;

    @GetMapping("/")
    ResponseEntity<List<Storehouse>> findAll(){

        List<Storehouse> result = storehouseService.findAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    void save(@RequestBody Storehouse storehouse){

        if(true){
            storehouseService.save(storehouse);
        }

    }

    @DeleteMapping("/")
    void delete(@RequestBody Storehouse storehouse){

        if(true){
            storehouseService.remove(storehouse);
        }

    }

}
