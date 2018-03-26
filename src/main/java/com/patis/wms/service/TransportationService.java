package com.patis.wms.service;

import com.patis.wms.entity.RequestItem;
import com.patis.wms.entity.Transportation;
import com.patis.wms.repository.RequestItemRepository;
import com.patis.wms.repository.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportationService {


    @Autowired
    private TransportationRepository repository;


    public Transportation findOne(long id){
        return repository.findOne(id);
    }
    public List<Transportation> findAll() {
        return repository.findAll();
    }

    public void save(Transportation transportation) {
        repository.save(transportation);
    }
    public void remove(Transportation transportation) {
        repository.delete(transportation);
    }
    public void remove(long id){
        repository.delete(id);
    }


}

