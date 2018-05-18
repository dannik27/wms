package com.patis.wms.report;


import com.patis.wms.entity.Task;
import com.patis.wms.service.TaskService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

  @Autowired
  WorkerReport workerReport;

  @Autowired
  TaskService taskService;

  @RequestMapping(value = "/worker", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> genWorkerReport() throws IOException {


    List<Task> tasks = taskService.findAll();


    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=workers.pdf");

    return ResponseEntity
      .ok()
      .headers(headers)
      .contentType(MediaType.APPLICATION_PDF)
      .body(workerReport.generatePdf(tasks));
  }
}
