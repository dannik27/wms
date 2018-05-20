package com.patis.wms.report;

import lombok.Data;

@Data
public class StorehouseReportItem {

  private int id;
  private String cell;
  private String product;
  private float capacity;
  private float busy;


}
