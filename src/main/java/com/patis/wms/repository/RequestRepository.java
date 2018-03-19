package com.patis.wms.repository;

import com.patis.wms.entity.Permission;
import com.patis.wms.entity.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {


}
