package com.patis.wms.controller;

import com.patis.wms.dto.PersonDTO;
import com.patis.wms.entity.Person;
import com.patis.wms.entity.Product;
import com.patis.wms.service.PersonService;
import com.patis.wms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    ResponseEntity<List<Product>> findAll(){

        List<Product> result = productService.findAll();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    void save(@RequestBody Product product){

        if(true){
            productService.save(product);
        }

    }

    @DeleteMapping("/{id_product}/")
    void delete(@PathVariable("id_product") long id_product){

        if(true){
            productService.remove(id_product);
        }

    }
}
