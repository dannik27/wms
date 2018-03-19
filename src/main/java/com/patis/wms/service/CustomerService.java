package com.patis.wms.service;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Customer;
import com.patis.wms.repository.CompanyRepository;
import com.patis.wms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    public Customer findOne(long id){
        return customerRepository.findOne(id);
    }
    public List<Customer> findAll(){
        return (List<Customer>) customerRepository.findAll();
    }
    public void save(Customer customer){
        customerRepository.save(customer);
    }
    public void remove(Customer customer){ customerRepository.delete(customer);}
    public void remove(long id){ customerRepository.delete(id);}

}
