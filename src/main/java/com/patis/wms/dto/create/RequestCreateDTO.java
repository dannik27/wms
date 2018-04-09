package com.patis.wms.dto.create;

import com.patis.wms.entity.OperationType;
import com.patis.wms.entity.Request;
import com.patis.wms.service.CustomerService;
import com.patis.wms.service.ProductService;
import com.patis.wms.service.StorehouseService;
import com.patis.wms.service.WorkerService;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCreateDTO implements Serializable {

  private long id;
  private long id_author;
  private long id_storehouse_from;
  private long id_storehouse_to;
  private LocalDate dateBegin;
  private long id_customer;
  private OperationType operationType;

  private List<RequestItemCreateDTO> items;


  public Request toEntity(WorkerService workerService, StorehouseService storehouseService,
                          CustomerService customerService, ProductService productService) {
    Request request = new Request();
    request.setId(id);
    request.setAuthor(workerService.findOne(id_author));
    request.setStorehouseFrom(storehouseService.findOne(id_storehouse_from));
    request.setStorehouseTo(storehouseService.findOne(id_storehouse_to));
    request.setDateBegin(dateBegin);
    request.setCustomer(customerService.findOne(id_customer));
    request.setRequestItems(new ArrayList<>());
    items.stream().map(i -> i.toEntity(productService)).forEach(i -> {
      request.getRequestItems().add(i);
      i.setRequest(request);
    });
    request.setOperationType(operationType);

    return request;
  }
}
