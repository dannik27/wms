package com.patis.wms.dto;

import com.patis.wms.entity.Role;
import com.patis.wms.entity.Worker;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class WorkerDTO {

    private long id;
    private PersonDTO person;
    private Role role;
    private LocalDate dateHired;
    private LocalDate dateFired;

    public WorkerDTO(Worker worker){
        id = worker.getId();
        person = new PersonDTO(worker.getPerson());
        role = worker.getRole();
        dateHired = worker.getDateHired();
        dateFired = worker.getDateFired();
    }

    public Worker toEntity(){
        Worker worker = new Worker();
        worker.setId(id);
        worker.setPerson(person.toEntity());
        worker.setRole(role);
        worker.setDateFired(dateFired);
        worker.setDateHired(dateHired);
        return worker;
    }


}
