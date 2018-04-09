package com.patis.wms.dto.create;

import com.patis.wms.entity.Product;
import com.patis.wms.entity.RequestItem;
import com.patis.wms.service.ProductService;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestItemCreateDTO implements Serializable {

    private long id;
    private int count;
    private long id_product;


    public RequestItem toEntity(ProductService productService){
        RequestItem requestItem = new RequestItem();
        requestItem.setId(id);
        requestItem.setCount(count);
        requestItem.setProduct(productService.findOne(id_product));
        return requestItem;
    }
}
