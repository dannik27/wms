package com.patis.wms.repository;

import com.patis.wms.entity.Storehouse;
import com.patis.wms.entity.StorehouseCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StorehouseCellRepository extends JpaRepository<StorehouseCell, Long> {


}
