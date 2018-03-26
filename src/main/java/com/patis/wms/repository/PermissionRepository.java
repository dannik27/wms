package com.patis.wms.repository;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {


}
