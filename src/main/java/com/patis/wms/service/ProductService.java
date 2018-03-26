package com.patis.wms.service;

import com.patis.wms.entity.Product;
import com.patis.wms.entity.Role;
import com.patis.wms.repository.ProductRepository;
import com.patis.wms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository repository;

    public Product findOne(long id){
        return repository.findOne(id);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public void save(Product product) {
        repository.save(product);
    }

    public void remove(Product product) {
        repository.delete(product);
    }

    public void remove(long id){
        repository.delete(id);
    }

}

