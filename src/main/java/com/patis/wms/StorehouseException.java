package com.patis.wms;

public class StorehouseException extends Exception {

  private String text;

  public StorehouseException(String message) {
    text = message;
  }

  @Override
  public String getMessage() {
    return text;
  }
}
