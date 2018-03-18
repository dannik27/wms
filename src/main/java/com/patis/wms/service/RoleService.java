package com.patis.wms.service;

import com.patis.wms.entity.Person;
import com.patis.wms.entity.Role;
import com.patis.wms.repository.PersonRepository;
import com.patis.wms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {


    @Autowired
    private RoleRepository roleRepository;

    public Role findOne(long id){
        return roleRepository.findOne(id);
    }

    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public void remove(Role role) {
        roleRepository.delete(role);
    }

    public void remove(long id){
        roleRepository.delete(id);
    }

}

