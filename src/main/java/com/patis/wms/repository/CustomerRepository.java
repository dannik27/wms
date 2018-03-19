package com.patis.wms.repository;

import com.patis.wms.entity.Customer;
import com.patis.wms.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {


}
