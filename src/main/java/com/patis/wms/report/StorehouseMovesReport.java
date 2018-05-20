package com.patis.wms.report;

import com.patis.wms.entity.Distribution;
import com.patis.wms.entity.Storehouse;
import com.patis.wms.service.StorehouseService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class StorehouseMovesReport {

  @Autowired
  StorehouseService storehouseService;

  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  public byte[] generatePdf(Optional<LocalDate> dateFrom, Optional<LocalDate> dateTo, long storehouseId){

    byte[] pdf = null;

    Storehouse storehouse = storehouseService.findOne(storehouseId);

    List<StorehouseMoveReportItem> items = storehouse.getStorehouseCells().stream()
      .flatMap(c -> c.getDistributions().stream())
      .filter(d -> d.getTaskItem().getTask().getTimeEnd() != null)
      .filter(d -> ! dateFrom.isPresent() || d.getTaskItem().getTask().getTimeEnd().isAfter(LocalDateTime.of(dateFrom.get(), LocalTime.MIN)))
      .filter(d -> ! dateTo.isPresent() || d.getTaskItem().getTask().getTimeEnd().isBefore(LocalDateTime.of(dateTo.get(), LocalTime.MAX)))
      .map(this::createReportItem)
      .sorted(Comparator.comparingInt(StorehouseMoveReportItem::getId))
      .collect(Collectors.toList());





    try {


      Map<String, Object> parameters = new HashMap<>();
      parameters.put("dataSource", new JRBeanCollectionDataSource(items));
      parameters.put("storehouseName", storehouse.getName());
      parameters.put("dateFromField", "С: " + (dateFrom.map(date -> dateFormatter.format(date)).orElse("C начала времён")));
      parameters.put("dateToField", "По: " + (dateTo.map(date -> dateFormatter.format(date)).orElse("До скончания дней")));
      parameters.put("rowCountField", "Кол-во строк: " + items.size());
      parameters.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));

      Resource template = new ClassPathResource("reports/storehouse-moves.jasper");
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

  public StorehouseMoveReportItem createReportItem(Distribution distribution){
    StorehouseMoveReportItem item = new StorehouseMoveReportItem();
    item.setId((int) distribution.getId());
    item.setDate(dateFormatter.format(distribution.getTaskItem().getTask().getTimeEnd()));
    item.setCell(distribution.getStorehouseCell().getName());
    item.setCount(distribution.getCount());
    item.setOperation(distribution.getCount() > 0 ? "поступление" : "отгрузка");
    item.setProduct(distribution.getTaskItem().getProduct().getName());
    item.setWorker(distribution.getTaskItem().getTask().getWorker().getPerson().getFio());
    return item;
  }

}
