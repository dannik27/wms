package com.patis.wms.dto;

import com.patis.wms.entity.Product;
import com.patis.wms.entity.Request;
import com.patis.wms.entity.RequestItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestItemDTO {

    private long id;
    private int count;
    private Product product;

    public RequestItemDTO(RequestItem requestItem){
        id = requestItem.getId();
        count = requestItem.getCount();
        product = requestItem.getProduct();
    }

    public RequestItem toEntity(){
        RequestItem requestItem = new RequestItem();
        requestItem.setId(id);
        requestItem.setCount(count);
        requestItem.setProduct(product);
        return requestItem;
    }
}
