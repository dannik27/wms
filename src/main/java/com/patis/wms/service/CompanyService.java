package com.patis.wms.service;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Person;
import com.patis.wms.repository.CompanyRepository;
import com.patis.wms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {


    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll(){
        return (List<Company>) companyRepository.findAll();
    }
    public void save(Company company){
        companyRepository.save(company);
    }

}