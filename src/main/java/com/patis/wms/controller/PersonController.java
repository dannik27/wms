package com.patis.wms.controller;

import com.patis.wms.entity.Person;
import com.patis.wms.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping({"/", " "})
    ResponseEntity<List<Person>> findAll(){

        List<Person> result = personService.findAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    void save(@RequestBody Person person){

        if(true){
            personService.save(person);
        }

    }

    @DeleteMapping("/")
    void delete(@RequestBody Person person){

        if(true){
            personService.remove(person);
        }

    }
}
