package com.patis.wms.repository;

import com.patis.wms.entity.Request;
import com.patis.wms.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface TransportationRepository extends JpaRepository<Transportation, Long> {


}
