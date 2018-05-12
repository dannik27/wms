package com.patis.wms.controller;

import com.patis.wms.StorehouseException;
import com.patis.wms.dto.TransportationDTO;
import com.patis.wms.dto.create.TransportationCreateDTO;
import com.patis.wms.entity.Transportation;
import com.patis.wms.service.RequestService;
import com.patis.wms.service.TaskManagerService;
import com.patis.wms.service.TaskService;
import com.patis.wms.service.TransportCompanyService;
import com.patis.wms.service.TransportationService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transportation")
public class TransportationController {

    @Autowired
    TransportationService transportationService;

    @Autowired
    TaskManagerService taskManagerService;

    @Autowired
    TaskService taskService;

    @Autowired
    RequestService requestService;

    @Autowired
    TransportCompanyService transportCompanyService;

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
    long save(@RequestBody TransportationCreateDTO transportationDTO){

        Transportation transportation = transportationService.save(transportationDTO.toEntity(requestService, transportCompanyService));
        return transportation.getId();

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
