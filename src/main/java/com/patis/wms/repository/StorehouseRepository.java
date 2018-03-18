package com.patis.wms.repository;

import com.patis.wms.entity.Customer;
import com.patis.wms.entity.Storehouse;
import org.springframework.data.repository.CrudRepository;

public interface StorehouseRepository extends CrudRepository<Storehouse, Long> {


}
