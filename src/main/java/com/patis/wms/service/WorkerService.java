package com.patis.wms.service;

import com.patis.wms.entity.Worker;
import com.patis.wms.repository.WorkerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Worker save(Worker worker) {
        return repository.save(worker);
    }
    public void remove(Worker worker) {
        repository.delete(worker);
    }
    public void remove(long id){
        repository.delete(id);
    }
    public Worker authorization(String login, String password){
        return repository.findTop1ByLoginAndPassword(login, password);
    }

}

