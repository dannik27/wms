package com.patis.wms.repository;

import com.patis.wms.entity.Customer;
import com.patis.wms.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
