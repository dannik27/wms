package com.patis.wms.dto.create;

import com.patis.wms.dto.PersonDTO;
import com.patis.wms.entity.Company;
import com.patis.wms.service.PersonService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class CompanyCreateDTO {




  private long id;
  private String name;
  private String description;
  private String inn;
  private String kpp;
  private String okpo;
  private String email;
  private String phone;

  private long id_person;


  public Company toEntity(PersonService personService){
    Company company = new Company();
    company.setId(id);
    company.setName(name);
    company.setDescription(description);
    company.setInn(inn);
    company.setOkpo(okpo);
    company.setEmail(email);
    company.setPhone(phone);
    company.setContactPerson(personService.findOne(id_person));
    return company;
  }


}
