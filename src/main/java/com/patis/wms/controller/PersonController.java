package com.patis.wms.controller;

import com.patis.wms.dto.PersonDTO;
import com.patis.wms.dto.create.PersonCreateDTO;
import com.patis.wms.entity.Person;
import com.patis.wms.service.PersonService;
import java.util.stream.Collectors;
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
    ResponseEntity<List<PersonDTO>> findAll(){

        List<PersonDTO> result = personService.findAll().stream().map(PersonDTO::new).collect(
            Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    long save(@RequestBody PersonCreateDTO personDTO){

        Person person = personService.save(personDTO.toEntity());
        return person.getId();

    }

    @DeleteMapping("/{id_person}/")
    void delete(@PathVariable("id_person") long id_person){

        if(true){
            personService.remove(id_person);
        }

    }
}
