package com.patis.wms.service;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Storehouse;
import com.patis.wms.repository.CompanyRepository;
import com.patis.wms.repository.StorehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorehouseService {


    @Autowired
    private StorehouseRepository storehouseRepository;

    public Storehouse findOne(long id){
        return storehouseRepository.findOne(id);
    }
    public List<Storehouse> findAll(){
        return storehouseRepository.findAll();
    }
    public void save(Storehouse storehouse){
        storehouseRepository.save(storehouse);
    }
    public void remove(Storehouse storehouse){ storehouseRepository.delete(storehouse);}
    public void remove(long id){storehouseRepository.delete(id);}
}
