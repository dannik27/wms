package com.patis.wms.dto;

import com.patis.wms.entity.Distribution;
import com.patis.wms.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class DistributionDTO {

    private long id;
    private int count;
    private boolean done;
    private Product product;
    private StorehouseCellDTO cell; // remove product;

    public DistributionDTO(Distribution distribution){
        id = distribution.getId();
        count = distribution.getCount();
        done = distribution.isDone();
        product = distribution.getTaskItem().getProduct();
        cell = new StorehouseCellDTO(distribution.getStorehouseCell());
        cell.setProduct(null);
    }

}
