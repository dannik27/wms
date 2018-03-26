package com.patis.wms.repository;

import com.patis.wms.entity.Person;
import com.patis.wms.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {


}
