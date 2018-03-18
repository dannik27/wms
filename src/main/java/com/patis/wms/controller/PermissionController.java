package com.patis.wms.controller;

import com.patis.wms.entity.Permission;
import com.patis.wms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/")
    ResponseEntity<List<Permission>> getAll(){
        List<Permission> result = permissionService.findAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
