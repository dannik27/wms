package com.patis.wms.dto;

import com.patis.wms.entity.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PersonDTO {

    private long id;
    private String name;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String email;

    public PersonDTO(Person person){
        id = person.getId();
        name = person.getName();
        lastName = person.getLastName();
        middleName = person.getMiddleName();
        birthDate = person.getBirthDate();
        email = person.getEmail();
    }

    public Person toEntity(){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setBirthDate(birthDate);
        person.setEmail(email);
        return person;
    }
}
