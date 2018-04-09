package com.patis.wms.controller;

import com.patis.wms.dto.WorkerDTO;
import com.patis.wms.dto.create.WorkerCreateDTO;
import com.patis.wms.entity.Role;
import com.patis.wms.entity.Worker;
import com.patis.wms.service.PersonService;
import com.patis.wms.service.RoleService;
import com.patis.wms.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @Autowired
    PersonService personService;

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    ResponseEntity<List<Worker>> findAll(){

        List<Worker> result = workerService.findAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    long save(@RequestBody WorkerCreateDTO workerDTO){

        Worker worker = workerService.save(workerDTO.toEntity(roleService, personService));
        return worker.getId();
    }

    @DeleteMapping("/{id_worker}/")
    void delete(@PathVariable("id_worker") long id_worker){

        if(true){
            workerService.remove(id_worker);
        }
    }

    @PostMapping("/{id_worker}/role/")
    ResponseEntity<Worker> gerRole(
            @PathVariable("id_worker") long id_worker,
            @RequestBody long id_role
    ){

        Worker worker = workerService.findOne(id_worker);
        Role role = roleService.findOne(id_role);
        if(role != null){
            worker.setRole(role);
            workerService.save(worker);
            return new ResponseEntity<>(worker, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
