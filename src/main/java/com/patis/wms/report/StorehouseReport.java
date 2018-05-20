package com.patis.wms.report;

import com.patis.wms.entity.Distribution;
import com.patis.wms.entity.Storehouse;
import com.patis.wms.entity.StorehouseCell;
import com.patis.wms.service.StorehouseService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StorehouseReport {

  @Autowired
  StorehouseService storehouseService;


  public byte[] generatePdf(long storehouseId){

    byte[] pdf = null;

    Storehouse storehouse = storehouseService.findOne(storehouseId);

    List<StorehouseReportItem> items = storehouse.getStorehouseCells().stream()
            .map(this::createReportItem)
            .sorted(Comparator.comparingInt(StorehouseReportItem::getId))
            .collect(Collectors.toList());


    try {


      Map<String, Object> parameters = new HashMap<>();
      parameters.put("dataSource", new JRBeanCollectionDataSource(items));
      parameters.put("storehouseName", storehouse.getName());
      parameters.put("rowCountField", "Кол-во строк: " + items.size());
      parameters.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));

      Resource template = new ClassPathResource("reports/storehouse.jasper");
      JasperPrint jasperPrint = JasperFillManager.fillReport(template.getInputStream(), parameters, new JREmptyDataSource());
      pdf = JasperExportManager.exportReportToPdf(jasperPrint);


    } catch (JRException ex) {
      ex.printStackTrace();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return pdf;
  }

  public StorehouseReportItem createReportItem(StorehouseCell cell){
    StorehouseReportItem item = new StorehouseReportItem();
    item.setId((int) cell.getId());
    item.setCell(cell.getName());
    if(cell.getProduct() != null){
      item.setProduct(cell.getProduct().getName());
    }else{
      item.setProduct("-");
    }

    item.setCapacity(cell.getCapacity());
    item.setBusy(cell.getBusy());
    return item;
  }

}
