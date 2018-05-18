package com.patis.wms.report;


import com.patis.wms.entity.Task;
import com.patis.wms.service.TaskService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

  @Autowired
  WorkerReport workerReport;

  @Autowired
  TaskService taskService;

  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  @RequestMapping(value = "/worker", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> genWorkerReport(
    @RequestParam("dateFrom") @DateTimeFormat(pattern="dd.MM.yyyy") LocalDate dateFrom,
    @RequestParam("dateTo") @DateTimeFormat(pattern="dd.MM.yyyy") LocalDate dateTo
  ) throws IOException {



    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=workers.pdf");

    return ResponseEntity
      .ok()
      .headers(headers)
      .contentType(MediaType.APPLICATION_PDF)
      .body(workerReport.generatePdf(dateFrom, dateTo));
  }

  @RequestMapping(value = "/storehouse/{id_storehouse}", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> getStorehouseReport(
    @PathVariable("id_storehouse") long id_storehouse
  ) throws IOException {

    List<Task> tasks = taskService.findAll();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=workers.pdf");

    return ResponseEntity
      .ok()
      .headers(headers)
      .contentType(MediaType.APPLICATION_PDF)
      .body(null);

  }

  @RequestMapping(value = "/storehouse/{id_storehouse}/moves", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> getStorehouseMovesReport(
    @RequestParam("dateFrom") @DateTimeFormat(pattern="dd.MM.yyyy") LocalDate dateFrom,
    @RequestParam("dateTo") @DateTimeFormat(pattern="dd.MM.yyyy") LocalDate dateTo,
    @PathVariable("id_storehouse") long id_storehouse
  ) throws IOException {

    List<Task> tasks = taskService.findAll();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=workers.pdf");

    return ResponseEntity
      .ok()
      .headers(headers)
      .contentType(MediaType.APPLICATION_PDF)
      .body(null);

  }
}
