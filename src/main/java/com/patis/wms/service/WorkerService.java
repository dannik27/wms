package com.patis.wms.service;

import com.patis.wms.dto.WorkerDTO;
import com.patis.wms.entity.Role;
import com.patis.wms.entity.Worker;
import com.patis.wms.repository.RoleRepository;
import com.patis.wms.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WorkerService {


    @Autowired
    private WorkerRepository repository;

    public Worker findOne(long id){
        return repository.findOne(id);
    }
    public List<Worker> findAll() {

        return repository.findAll();
    }

    public void save(Worker worker) {
        repository.save(worker);
    }
    public void remove(Worker worker) {
        repository.delete(worker);
    }
    public void remove(long id){
        repository.delete(id);
    }

}

