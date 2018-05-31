package com.patis.wms.workflow.controller;

import com.patis.wms.dto.create.RequestCreateDTO;
import com.patis.wms.dto.create.TransportationCreateDTO;
import com.patis.wms.entity.Transportation;
import com.patis.wms.service.TransportationService;
import com.patis.wms.workflow.TaskDTO;
import com.patis.wms.workflow.service.GetFromCustomer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("getFromCustomer")
public class GetFromCustomerController {

  @Autowired
  RuntimeService runtimeService;

  @Autowired
  TaskService taskService;

  @Autowired
  GetFromCustomer getFromCustomer;

  @Autowired
  TransportationService transportationService;

  @PostMapping("/start/")
  void start(@RequestBody RequestCreateDTO requestCreateDTO){

    getFromCustomer.start ( requestCreateDTO );

  }

  @PostMapping("/createTransportation")
  void createTransportation(
      @RequestBody TransportationCreateDTO transportationCreateDTO){

      getFromCustomer.createTransportation ( transportationCreateDTO );

  }

  @PostMapping("/receiveTransportation/{id_transportation}")
  void receiveTransportation(
      HttpServletRequest request,
      @RequestBody LocalDateTime dateReceived,
      @PathVariable("id_transportation") long transportationId) {

    Transportation transportation = transportationService.findOne(transportationId);
    if((transportation != null) && (transportation.getDateReceived() == null)){
      getFromCustomer.receiveTransportation ( dateReceived, transportationId );
    }
  }

  @PostMapping("/v2/receiveTransportation/{id_transportation}")
  void receiveTransportationv2(
          @RequestBody String dateReceivedStr,
          @PathVariable("id_transportation") long transportationId) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH-mm");
    LocalDateTime dateReceived = LocalDateTime.parse(dateReceivedStr, formatter);

    Transportation transportation = transportationService.findOne(transportationId);
    if((transportation != null) && (transportation.getDateReceived() == null)){
      getFromCustomer.receiveTransportation ( dateReceived, transportationId );
    }
  }

  @PostMapping("/complete/{id_request}")
  void completeTask(
      @PathVariable("id_request") long requestId) {


    getFromCustomer.complete ( requestId );

  }


  @GetMapping("/tasks")
  List<TaskDTO> getTasks(){
    return taskService.createTaskQuery().active().list().stream().map(TaskDTO::new).collect(
        Collectors.toList());

  }



}
