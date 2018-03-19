package com.patis.wms.service;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Distribution;
import com.patis.wms.repository.CompanyRepository;
import com.patis.wms.repository.DistributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionService {


    @Autowired
    private DistributionRepository distributionRepository;

    public List<Distribution> findAll(){
        return (List<Distribution>) distributionRepository.findAll();
    }
    public void save(Distribution distribution){
        distributionRepository.save(distribution);
    }
    public void remove(Distribution distribution){ distributionRepository.delete(distribution);}
}
