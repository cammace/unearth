package com.cammace.unearth.wbgetentities.model;

import com.squareup.moshi.Json;

public class DataValue {

  @Json(name = "value")
  private String value;

  @Json(name = "type")
  private String type;

  public String getValue() {
    return value;
  }

  public String getType() {
    return type;
  }
}
