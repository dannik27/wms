package com.patis.wms.service;

import com.patis.wms.entity.Request;
import com.patis.wms.entity.RequestItem;
import com.patis.wms.repository.RequestItemRepository;
import com.patis.wms.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestItemService {


    @Autowired
    private RequestItemRepository repository;

    public List<RequestItem> findByRequest(long id_request){
        return repository.findAllByRequest_Id(id_request);
    }
    public RequestItem findOne(long id){
        return repository.findOne(id);
    }
    public List<RequestItem> findAll() {
        return (List<RequestItem>) repository.findAll();
    }

    public void save(RequestItem requestItem) {
        repository.save(requestItem);
    }
    public void remove(RequestItem requestItem) {
        repository.delete(requestItem);
    }
    public void remove(long id){
        repository.delete(id);
    }

}

