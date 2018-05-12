package com.patis.wms.dto.create;

import com.patis.wms.entity.Worker;
import com.patis.wms.service.PersonService;
import com.patis.wms.service.RoleService;
import java.time.LocalDate;

public class WorkerCreateDTO {

  private long id;
  private long id_person;
  private long id_role;
  private LocalDate dateHired;
  private LocalDate dateFired;
  private String login;
  private String password;


  public Worker toEntity(RoleService roleService, PersonService personService){
    Worker worker = new Worker();
    worker.setId(id);
    worker.setPerson(personService.findOne(id_person));
    worker.setRole(roleService.findOne(id_role));
    worker.setDateFired(dateFired);
    worker.setDateHired(dateHired);
    worker.setLogin(login);
    worker.setPassword(password);
    return worker;
  }

}
