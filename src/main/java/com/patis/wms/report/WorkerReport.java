package com.patis.wms.report;

import com.patis.wms.entity.Task;
import com.patis.wms.service.TaskService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class WorkerReport {

  @Autowired
  TaskService taskService;

  List<WorkerReportItem> items;
  WorkerReportItem tempItem;

  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  public byte[] generatePdf(Optional<LocalDate> dateFrom, Optional<LocalDate> dateTo){

    byte[] pdf = null;

    List<Task> data = taskService.findAll();

    items = new ArrayList<>();


    data.stream()
      .filter(t -> ! dateFrom.isPresent() || t.getTimeEnd().isAfter(LocalDateTime.of(dateFrom.get(), LocalTime.MIN)))
      .filter(t -> ! dateTo.isPresent() || t.getTimeEnd().isBefore(LocalDateTime.of(dateTo.get(), LocalTime.MAX)))
      .peek(t -> tempItem = findItem(items, t))
      .flatMap(t -> t.getTaskItems().stream())
      .flatMap(ti -> ti.getDistributions().stream())
      .forEach(d->{
        tempItem.setDone(tempItem.getDone() + 1);
        tempItem.setWeight(tempItem.getWeight() + Math.abs(d.getCount()) * d.getTaskItem().getProduct().getVolume());
      });

    items.sort(Comparator.comparingInt(WorkerReportItem::getId));

    List<ChartItem> chartItems = items.stream().map(this::toChartItem).collect(Collectors.toList());

    try {


      Map<String, Object> parameters = new HashMap<>();
      parameters.put("dataSource", new JRBeanCollectionDataSource(items));
      parameters.put("chartDatasource", new JRBeanCollectionDataSource(chartItems));
      parameters.put("dateFromField", "С: " + (dateFrom.map(date -> dateFormatter.format(date)).orElse("C начала времён")));
      parameters.put("dateToField", "По: " + (dateTo.map(date -> dateFormatter.format(date)).orElse("До скончания дней")));
      parameters.put("rowCountField", "Кол-во строк: " + items.size());
      //parameters.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));

      Resource template = new ClassPathResource("reports/kek.jasper");
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


  private WorkerReportItem findItem(List<WorkerReportItem> items, Task task){
    for (WorkerReportItem workerReportItem : items){
      if(workerReportItem.getId() == task.getWorker().getId()){
        return workerReportItem;
      }
    }

    WorkerReportItem workerReportItem = new WorkerReportItem();
    workerReportItem.setId((int)task.getWorker().getId());
    workerReportItem.setName(task.getWorker().getPerson().getFio());
    workerReportItem.setStorehouse(task.getWorker().getStorehouse().getName());
    items.add(workerReportItem);
    return workerReportItem;
  }


  private ChartItem toChartItem(WorkerReportItem w){
    return new ChartItem(w.getName(), w.getName(), w.getWeight(), w.getWeight() + " кг.");
  }


}
