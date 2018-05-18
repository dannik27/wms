package com.patis.wms.report;

import lombok.Data;

@Data
public class WorkerReportItem {

  private int id;
  private String name;
  private String storehouse;
  private int done;
  private float weight;

}
