package com.patis.wms.dto;


import com.patis.wms.entity.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class CompanyDTO {

  private long id;
  private String name;
  private String description;
  private String inn;
  private String kpp;
  private String okpo;
  private String email;
  private String phone;

  private PersonDTO contactPerson;

  public CompanyDTO(Company company){
    id = company.getId();
    name = company.getName();
    description = company.getDescription();
    inn = company.getInn();
    okpo = company.getOkpo();
    email = company.getEmail();
    phone = company.getPhone();
    if(company.getContactPerson() != null){
      contactPerson = new PersonDTO(company.getContactPerson());
    }

  }

  public Company toEntity(){
    Company company = new Company();
    company.setId(id);
    company.setName(name);
    company.setDescription(description);
    company.setInn(inn);
    company.setOkpo(okpo);
    company.setEmail(email);
    company.setPhone(phone);
    if(contactPerson != null){
      company.setContactPerson(contactPerson.toEntity());
    }
    return company;
  }



}
