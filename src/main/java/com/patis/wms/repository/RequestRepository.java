package com.patis.wms.repository;

import com.patis.wms.entity.Permission;
import com.patis.wms.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface RequestRepository extends JpaRepository<Request, Long> {


}
