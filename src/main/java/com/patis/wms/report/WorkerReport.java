package com.patis.wms.report;

import com.patis.wms.entity.Distribution;
import com.patis.wms.entity.Task;
import com.patis.wms.entity.TaskItem;
import com.patis.wms.service.TaskService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

  public byte[] generatePdf(LocalDate dateFrom, LocalDate dateTo){

    byte[] pdf = null;

    List<Task> data = taskService.findAll();

    items = new ArrayList<>();


    for(Task task : data){

      if((task.getTimeEnd().isBefore(LocalDateTime.of(dateFrom, LocalTime.MIN))) || (task.getTimeEnd().isAfter(LocalDateTime.of(dateTo, LocalTime.MAX)))){
        continue;
      }

      tempItem = findItem(items, task);

//      task.getTaskItems().stream()
//        .flatMap(ti -> ti.getDistributions().stream())
//        .filter(Distribution::isDone)
//        .forEach(d->{
//          tempItem.setDone(tempItem.getDone() + 1);
//          tempItem.setWeight(tempItem.getWeight() + Math.abs(d.getCount()) * d.getTaskItem().getProduct().getVolume());
//        });

      for(TaskItem taskItem : task.getTaskItems()){
        for(Distribution distribution : taskItem.getDistributions()){
          if(distribution.isDone()){
            tempItem.setDone(tempItem.getDone() + 1);
            tempItem.setWeight(tempItem.getWeight() + Math.abs(distribution.getCount()) * distribution.getTaskItem().getProduct().getVolume());
          }
        }
      }

    }


    try {


      JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(items);
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("dataSource", itemsJRBean);
      parameters.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));

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


}
