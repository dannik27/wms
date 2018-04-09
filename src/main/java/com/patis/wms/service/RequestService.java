package com.patis.wms.service;

import com.patis.wms.dto.RequestDTO;
import com.patis.wms.dto.WorkerDTO;
import com.patis.wms.entity.Request;
import com.patis.wms.entity.Worker;
import com.patis.wms.repository.RequestRepository;
import com.patis.wms.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RequestService {


    @Autowired
    private RequestRepository repository;

    public Request findOne(long id){
        return repository.findOne(id);
    }
    public List<Request> findAll() {
        return repository.findAll();

    }

    public Request save(Request request) {
        return repository.save(request);
    }
    public void remove(Request request) {
        repository.delete(request);
    }
    public void remove(long id){
        repository.delete(id);
    }

}

