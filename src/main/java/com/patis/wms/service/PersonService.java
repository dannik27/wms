package com.patis.wms.service;

import com.patis.wms.entity.Person;
import com.patis.wms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {


    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        return (List<Person>) personRepository.findAll();
    }
    public void save(Person person){
        personRepository.save(person);
    }
    public void remove(Person person){ personRepository.delete(person);}
}
