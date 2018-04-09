package com.patis.wms.service;

import com.patis.wms.dto.PersonDTO;
import com.patis.wms.entity.Person;
import com.patis.wms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {


    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findOne(long id) {
        return personRepository.findOne(id);
    }
    public Person save(Person person){
        return personRepository.save(person);
    }
    public void remove(Person person){ personRepository.delete(person);}
    public void remove(long id){ personRepository.delete(id);}

}
