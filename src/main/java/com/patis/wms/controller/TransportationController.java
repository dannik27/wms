package com.patis.wms.controller;

import com.patis.wms.StorehouseException;
import com.patis.wms.dto.StorehouseDTO;
import com.patis.wms.dto.TransportationDTO;
import com.patis.wms.entity.Transportation;
import com.patis.wms.service.TaskManagerService;
import com.patis.wms.service.TaskService;
import com.patis.wms.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transportation")
public class TransportationController {

    @Autowired
    TransportationService transportationService;

    @Autowired
    TaskManagerService taskManagerService;

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

    @PostMapping("/{id_transportation}/start")
    String startTransportation(
            @PathVariable("id_transportation") long id_transportation,
            @RequestBody LocalDateTime dateShipped
    ){

        Transportation transportation = transportationService.findOne(id_transportation);
        try {
            taskManagerService.transportationStart(transportation, dateShipped);
        } catch (StorehouseException e) {
            return e.getMessage();
        }
        return "ok";

    }

    @PostMapping("/{id_transportation}/receive")
    String receiveTransportation(
            @PathVariable("id_transportation") long id_transportation,
            @RequestBody LocalDateTime dataReceived
    ){

        Transportation transportation = transportationService.findOne(id_transportation);
        try {
            taskManagerService.transportationReceived(transportation, dataReceived);
        } catch (StorehouseException e) {
            return e.getMessage();
        }
        return "ok";
    }

}
