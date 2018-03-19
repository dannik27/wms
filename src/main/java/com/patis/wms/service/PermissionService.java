package com.patis.wms.service;

import com.patis.wms.entity.Permission;
import com.patis.wms.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public List<Permission> findAll(){
        return (List<Permission>) permissionRepository.findAll();
    }

    public Permission findOne(long id){
        return permissionRepository.findOne(id);
    }


}
