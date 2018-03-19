package com.patis.wms.entity;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Worker {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn(name="id_person")
    private Person person;


    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_role")
    private Role role;


    private LocalDate dateHired;


    private LocalDate dateFired;



}
