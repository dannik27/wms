package com.patis.wms.controller;

import com.patis.wms.entity.Permission;
import com.patis.wms.entity.Person;
import com.patis.wms.entity.Role;
import com.patis.wms.service.PermissionService;
import com.patis.wms.service.PersonService;
import com.patis.wms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @GetMapping("/")
    ResponseEntity<List<Role>> findAll(){

        List<Role> result = roleService.findAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    void save(@RequestBody Role role){

        if(true){
            roleService.save(role);
        }
    }

    @DeleteMapping("/{id_role}/")
    void delete(@PathVariable("id_role") long id_role){

        if(true){
            roleService.remove(id_role);
        }
    }

    @GetMapping("/{id_role}/permission/")
    ResponseEntity<List<Permission>> findPermission(
            @PathVariable("id_role") long role_id
    ){

        Role role = roleService.findOne(role_id);
        if(role != null){
            return new ResponseEntity<>(role.getPermissions(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id_role}/permission/{id_permission}/")
    ResponseEntity<List<Permission>> deletePermission(
            @PathVariable("id_role") long id_role,
            @PathVariable("id_permission") long id_permission
    ){
        Permission permission = permissionService.findOne(id_permission);
        Role role = roleService.findOne(id_role);
        if(role != null){
            role.getPermissions().remove(permission);
            roleService.save(role);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{id_role}/permission/")
    ResponseEntity<Role> addPermission(
            @PathVariable("id_role") long id_role,
            @RequestBody Permission permission
    ){
        Role role = roleService.findOne(id_role);
        if(role != null){
            role.getPermissions().add(permission);
            roleService.save(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
