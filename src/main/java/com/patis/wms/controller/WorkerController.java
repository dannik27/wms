package com.patis.wms.controller;

import com.patis.wms.dto.WorkerDTO;
import com.patis.wms.dto.create.WorkerCreateDTO;
import com.patis.wms.entity.Role;
import com.patis.wms.entity.Worker;
import com.patis.wms.service.PersonService;
import com.patis.wms.service.RoleService;
import com.patis.wms.service.WorkerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @Autowired
    PersonService personService;

    @Autowired
    RoleService roleService;

    @GetMapping("/authorization")
    ResponseEntity<WorkerDTO> authorization(
      @RequestParam("login") String login,
      @RequestParam("password") String password)
    {

        Worker result = workerService.authorization(login, password);
        if(result != null){
            return new ResponseEntity<>(new WorkerDTO(result), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/")
    ResponseEntity<List<WorkerDTO>> findAll(){

        List<WorkerDTO> result = workerService.findAll().stream().map(WorkerDTO::new).collect(Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{worker_id}/")
    ResponseEntity<WorkerDTO> findOne(@PathVariable("worker_id") long worker_id){

        Worker result = workerService.findOne(worker_id);
        if(result != null){
            return new ResponseEntity<>(new WorkerDTO(result), HttpStatus.OK);
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
