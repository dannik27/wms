package com.patis.wms.service;

import com.patis.wms.entity.Task;
import com.patis.wms.repository.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {


    @Autowired
    private TaskRepository repository;

    public List<Task> findByWorker(long id_worker){ return repository.findByWorker_IdOrderByTimeBeginDesc(id_worker);}
    public Task findOne(long id){
        return repository.findOne(id);
    }
    public List<Task> findAll() {
        return repository.findAll();
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

