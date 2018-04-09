package com.patis.wms.controller;

import com.patis.wms.dto.PersonDTO;
import com.patis.wms.dto.RequestDTO;
import com.patis.wms.dto.RequestItemDTO;
import com.patis.wms.dto.create.RequestCreateDTO;
import com.patis.wms.dto.create.RequestItemCreateDTO;
import com.patis.wms.entity.Request;
import com.patis.wms.entity.RequestItem;
import com.patis.wms.service.CustomerService;
import com.patis.wms.service.PersonService;
import com.patis.wms.service.ProductService;
import com.patis.wms.service.RequestItemService;
import com.patis.wms.service.RequestService;
import com.patis.wms.service.StorehouseService;
import com.patis.wms.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    RequestService requestService;

    @Autowired
    RequestItemService requestItemService;

    @Autowired
    StorehouseService storehouseService;

    @Autowired
    CustomerService customerService;

    @Autowired
    WorkerService workerService;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    ResponseEntity<List<RequestDTO>> findAll(){

        List<RequestDTO> result = requestService.findAll()
                .stream().map(RequestDTO::new).collect(Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    long save(@RequestBody RequestCreateDTO requestDTO){

        Request request = requestService.save(requestDTO.toEntity(workerService, storehouseService, customerService));
        return request.getId();

    }

    @DeleteMapping("/{id_request}/")
    void delete(@PathVariable("id_request") long id_request){

        if(true){
            requestService.remove(id_request);
        }
    }

    @GetMapping("/{id_request}/item/")
    ResponseEntity<List<RequestItemDTO>> findAllItems(
            @PathVariable("id_request") long id_request
    ){

        List<RequestItemDTO> result = requestItemService.findByRequest(id_request)
                .stream().map(RequestItemDTO::new).collect(Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{id_request}/item/")
    ResponseEntity<RequestItemDTO> addItem(
            @PathVariable("id_request") long id_request,
            @RequestBody RequestItemCreateDTO requestItemDTO
    ){


        Request request = requestService.findOne(id_request);
        RequestItem requestItem = requestItemDTO.toEntity(productService);

        if(request != null){
            request.getRequestItems().add(requestItem);
            requestItem.setRequest(request);
            requestService.save(request);
            return new ResponseEntity<>(new RequestItemDTO(requestItem), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id_request}/item/{id_item}/")
    ResponseEntity<RequestItemDTO> deleteItem(
            @PathVariable("id_request") long id_request,
            @PathVariable("id_item") long id_item
    ){


        Request request = requestService.findOne(id_request);
        RequestItem requestItem = requestItemService.findOne(id_item);

        if(request != null){
            request.getRequestItems().remove(requestItem);
            requestService.save(request);
            requestItemService.remove(requestItem);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
