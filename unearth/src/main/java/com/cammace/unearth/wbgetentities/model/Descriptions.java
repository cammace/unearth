package com.cammace.unearth.wbgetentities.model;

import com.squareup.moshi.Json;

public class Descriptions {

  @Json(name = "en")
  private Language language;

  public Language getLanguage() {
    return language;
  }
}
