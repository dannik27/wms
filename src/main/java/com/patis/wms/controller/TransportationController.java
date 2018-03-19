package com.patis.wms.controller;

import com.patis.wms.dto.StorehouseDTO;
import com.patis.wms.dto.TransportationDTO;
import com.patis.wms.entity.Transportation;
import com.patis.wms.service.TaskService;
import com.patis.wms.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transportation")
public class TransportationController {

    @Autowired
    TransportationService transportationService;

    @Autowired
    TaskService taskService;

    @GetMapping("/")
    ResponseEntity<List<TransportationDTO>> findAll(){

        List<TransportationDTO> result = transportationService.findAll().stream().map(TransportationDTO::new).collect(Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    void save(@RequestBody TransportationDTO transportation){

        if(true){
            transportationService.save(transportation.toEntity(taskService));
        }

    }

}
