package com.patis.wms.repository;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Long> {


}
