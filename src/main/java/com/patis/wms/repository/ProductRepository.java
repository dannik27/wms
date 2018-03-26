package com.patis.wms.repository;

import com.patis.wms.entity.Permission;
import com.patis.wms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
