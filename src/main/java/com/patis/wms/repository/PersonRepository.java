package com.patis.wms.repository;

import com.patis.wms.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
