package com.patis.wms.repository;

import com.patis.wms.entity.Customer;
import com.patis.wms.entity.Storehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StorehouseRepository extends JpaRepository<Storehouse, Long> {


}
