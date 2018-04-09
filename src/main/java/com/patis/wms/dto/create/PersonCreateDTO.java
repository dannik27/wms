package com.patis.wms.dto.create;

import com.patis.wms.entity.Person;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonCreateDTO {

    private long id;
    private String name;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String email;

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
