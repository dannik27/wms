package com.patis.wms.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="person" )
    private List<Worker> jobs;

    private String name;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String email;



    public Person(String name, String lastName, String middleName, LocalDate birthDate, String email) {
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.email = email;
    }

}
