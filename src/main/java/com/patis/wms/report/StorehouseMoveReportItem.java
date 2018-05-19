package com.patis.wms.report;

import lombok.Data;

@Data
public class StorehouseMoveReportItem {

  private int id;
  private String date;
  private String product;
  private String operation;
  private float count;
  private String cell;
  private String worker;


}
