package com.patis.wms.dto.create;

import com.patis.wms.entity.Product;
import com.patis.wms.entity.StorehouseCell;
import com.patis.wms.service.ProductService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StorehouseCellCreateDTO {

    private long id;
    private String name;
    private float capacity;
    private float busy;
    private long id_product;


    public StorehouseCell toEntity(ProductService productService){
        StorehouseCell storehouseCell = new StorehouseCell();
        storehouseCell.setId(id);
        storehouseCell.setName(name);
        storehouseCell.setCapacity(capacity);
        storehouseCell.setProduct(productService.findOne(id_product));
        return storehouseCell;
    }

}
