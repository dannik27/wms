package com.patis.wms.service;

import com.patis.wms.entity.Task;
import com.patis.wms.entity.Transportation;
import com.patis.wms.repository.TaskRepository;
import com.patis.wms.repository.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    private TaskRepository repository;


    public Task findOne(long id){
        return repository.findOne(id);
    }
    public List<Task> findAll() {
        return (List<Task>) repository.findAll();
    }

    public void save(Task task) {
        repository.save(task);
    }
    public void remove(Task task) {
        repository.delete(task);
    }
    public void remove(long id){
        repository.delete(id);
    }

}

