package com.cammace.unearth.wbgetentities.model;

import com.squareup.moshi.Json;

import java.util.List;

public class Claim {

  @Json(name = "P18")
  private List<Foobar> image;

  @Json(name = "P856")
  private List<Foobar> website;

  public List<Foobar> getImage() {
    return image;
  }

  public List<Foobar> getWebsite() {
    return website;
  }
}
