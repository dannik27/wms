package com.patis.wms.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ChartItem {

  private String serie;
  private String key;
  private Number value;
  private String label;


}
