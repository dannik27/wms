package com.patis.wms.service;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.TransportCompany;
import com.patis.wms.repository.CompanyRepository;
import com.patis.wms.repository.TransportCompanyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportCompanyService {


    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    public List<TransportCompany> findAll(){
        return transportCompanyRepository.findAll();
    }
    public TransportCompany findOne(long id){
        return transportCompanyRepository.findOne(id);
    }
    public TransportCompany save(TransportCompany company){
        return transportCompanyRepository.save(company);
    }
    public void remove(TransportCompany company){ transportCompanyRepository.delete(company);}
    public void remove(long id){ transportCompanyRepository.delete(id);}
}
