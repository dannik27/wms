package com.patis.wms.workflow.controller;

import com.patis.wms.dto.create.RequestCreateDTO;
import com.patis.wms.dto.create.TransportationCreateDTO;
import com.patis.wms.workflow.TaskDTO;
import com.patis.wms.workflow.service.GetFromCustomer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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

  @PostMapping("/start/")
  void start(@RequestBody RequestCreateDTO requestCreateDTO){

    getFromCustomer.start ( requestCreateDTO );

  }

  @PostMapping("/createTransportation")
  void createTransportation(
      @RequestBody TransportationCreateDTO transportationCreateDTO){

      getFromCustomer.createTransportation ( transportationCreateDTO );

  }

  @PostMapping("/receiveTransportation/{id_request}")
  void receiveTransportation(
      @RequestBody LocalDateTime dateReceived,
      @PathVariable("id_request") long requestId) {

    getFromCustomer.receiveTransportation ( dateReceived, requestId );

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
