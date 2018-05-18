package com.patis.wms.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn(name="id_storehouse")
    private Storehouse storehouse;

    @OneToMany( cascade = CascadeType.ALL, mappedBy="worker" )
    private List<Task> tasks;

    private LocalDate dateHired;

    private LocalDate dateFired;

    private String login;
    private String password;

    public int getCurrentTaskCount(){
        return (int) tasks.stream().filter(task -> task.getTaskStatus() != TaskStatus.DONE)
                .flatMap(task -> task.getTaskItems().stream())
                .flatMap(taskItem -> taskItem.getDistributions().stream())
                .filter(distribution -> ! distribution.isDone())
                .count();
    }



}
