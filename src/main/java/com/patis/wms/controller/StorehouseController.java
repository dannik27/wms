package com.patis.wms.controller;


import com.patis.wms.dto.StorehouseCellDTO;
import com.patis.wms.dto.StorehouseDTO;
import com.patis.wms.entity.Customer;
import com.patis.wms.entity.Storehouse;
import com.patis.wms.entity.StorehouseCell;
import com.patis.wms.service.CustomerService;
import com.patis.wms.service.StorehouseService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("storehouse")
public class StorehouseController {

    @Autowired
    StorehouseService storehouseService;

    @GetMapping("/")
    ResponseEntity<List<StorehouseDTO>> findAll(){

        List<StorehouseDTO> result = storehouseService.findAll().stream().map(StorehouseDTO::new).collect(Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id_storehouse}/cell/")
    ResponseEntity<List<StorehouseCellDTO>> findCells(
            @PathVariable("id_storehouse") long id_storehouse
    ){

        Storehouse storehouse = storehouseService.findOne(id_storehouse);
        List<StorehouseCellDTO> result = storehouse.getStorehouseCells().stream().map(StorehouseCellDTO::new).collect(Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    void save(@RequestBody StorehouseDTO storehouse){

        if(true){
            storehouseService.save(storehouse.toEntity());
        }

    }

    @DeleteMapping("/{id_storehouse}/")
    void delete(
            @PathVariable("id_storehouse") long id_storehouse
    ){

        if(true){
            storehouseService.remove(id_storehouse);
        }

    }

    @PostMapping("/{id_storehouse}/cell/")
    ResponseEntity<StorehouseDTO> addCell(
            @PathVariable("id_storehouse") long id_storehouse,
            @RequestBody StorehouseCellDTO storehouseCellDTO
    ){

        Storehouse storehouse = storehouseService.findOne(id_storehouse);
        StorehouseCell storehouseCell = storehouseCellDTO.toEntity();

        if(storehouse != null){
            storehouse.getStorehouseCells().add(storehouseCell);
            storehouseCell.setStorehouse(storehouse);
            storehouseService.save(storehouse);
            return new ResponseEntity<>(new StorehouseDTO(storehouse), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/{id_storehouse}/cell/{id_storehouse_cell}")
    ResponseEntity<StorehouseDTO> removeCell(
            @PathVariable("id_storehouse") long id_storehouse,
            @PathVariable("id_storehouse_cell") long id_storehouse_cell
    ){

        Storehouse storehouse = storehouseService.findOne(id_storehouse);
        StorehouseCell storehouseCell = null;
        for(StorehouseCell cell : storehouse.getStorehouseCells()){
            if(cell.getId() == id_storehouse_cell){
                storehouseCell = cell;
            }
        }

        if(storehouseCell != null){
            storehouse.getStorehouseCells().remove(storehouseCell);
            storehouseService.save(storehouse);
            return new ResponseEntity<>(new StorehouseDTO(storehouse), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




}
