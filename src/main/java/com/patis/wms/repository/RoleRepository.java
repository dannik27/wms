package com.patis.wms.repository;

import com.patis.wms.entity.Permission;
import com.patis.wms.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {


}
