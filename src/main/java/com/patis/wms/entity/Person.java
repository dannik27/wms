package com.patis.wms.entity;



import com.google.gson.annotations.Expose;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Person {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Expose
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

    public String getFio(){
        return String.format("%s %s.%s.", lastName, name.charAt(0), middleName.charAt(0));
    }

}
