package com.cammace.unearth.wbgetentities.model;

import com.squareup.moshi.Json;

public class Mainsnak {

  @Json(name = "datavalue")
  private DataValue datavalue;


  public DataValue getDatavalue() {
    return datavalue;
  }
}
