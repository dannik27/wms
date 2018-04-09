package com.patis.wms.workflow.service;

import com.patis.wms.StorehouseException;
import com.patis.wms.dto.create.RequestCreateDTO;
import com.patis.wms.dto.create.TransportationCreateDTO;
import com.patis.wms.entity.Request;
import com.patis.wms.entity.Task;
import com.patis.wms.entity.TaskItem;
import com.patis.wms.entity.Transportation;
import com.patis.wms.service.CustomerService;
import com.patis.wms.service.ProductService;
import com.patis.wms.service.RequestService;
import com.patis.wms.service.StorehouseService;
import com.patis.wms.service.TaskManagerService;
import com.patis.wms.service.TransportationService;
import com.patis.wms.service.WorkerService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetFromCustomer {

  @Autowired
  RequestService requestService;

  @Autowired
  WorkerService workerService;

  @Autowired
  CustomerService customerService;

  @Autowired
  ProductService productService;

  @Autowired
  StorehouseService storehouseService;

  @Autowired
  TransportationService transportationService;

  @Autowired
  TaskManagerService taskManagerService;


  @Autowired
  TaskService taskService;
  @Autowired
  RuntimeService runtimeService;

  public void start(RequestCreateDTO requestCreateDTO){
    Map<String, Object> variables = new HashMap<>();
    variables.put("requestCreateDTO", requestCreateDTO);
    runtimeService.startProcessInstanceByKey("GetFromCustomer", variables);
  }

  public long createRequest(RequestCreateDTO requestCreateDTO){

    return requestService.save(requestCreateDTO.toEntity(workerService, storehouseService, customerService, productService)).getId();

  }

  public void createTransportation(TransportationCreateDTO transportationCreateDTO, String taskId){

    Map<String, Object> variables = new HashMap<> ();
    variables.put("transportationCreateDTO", transportationCreateDTO);
    taskService.complete(taskId, variables);

  }

  public boolean checkResources(long requestId){

    Task task = new Task();
    task.setTaskItems(new ArrayList<>());
    Request request = requestService.findOne(requestId);
    request.getRequestItems().stream()
        .map(TaskItem::new)
        .forEach(taskItem -> {
          task.getTaskItems().add(taskItem);
          taskItem.setTask(task);
        });

    try {
      taskManagerService.fillStorehouse(task, request.getStorehouseTo());
      return true;
    } catch (StorehouseException e) {
      return false;
    }

  }

  public long saveTransportation(TransportationCreateDTO transportationCreateDTO)
      throws StorehouseException {

    Transportation transportation = transportationService.save(transportationCreateDTO.toEntity(requestService));
    taskManagerService.transportationStart(transportation, LocalDateTime.now());
    return transportation.getId();
  }

  public void receiveTransportation(LocalDateTime dateReceived, String taskId){
    Map<String, Object> variables = new HashMap<>();
    variables.put("receiveDate", dateReceived);
    taskService.complete(taskId, variables);
  }

  public void createTasks(long id_transportation, LocalDateTime receiveDate)
      throws StorehouseException {

    Transportation transportation = transportationService.findOne(id_transportation);
    taskManagerService.transportationReceived(transportation, receiveDate);
  }

  public void complete(String taskId){
    taskService.complete(taskId);
  }



}
