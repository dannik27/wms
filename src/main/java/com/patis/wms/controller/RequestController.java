package com.patis.wms.controller;

import com.patis.wms.dto.PersonDTO;
import com.patis.wms.dto.RequestDTO;
import com.patis.wms.entity.Request;
import com.patis.wms.service.PersonService;
import com.patis.wms.service.RequestService;
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
    void save(@RequestBody Request request){

        if(true){
            requestService.save(request);
        }

    }

    @DeleteMapping("/{id_request}/")
    void delete(@PathVariable("id_request") long id_request){

        if(true){
            requestService.remove(id_request);
        }

    }
}
