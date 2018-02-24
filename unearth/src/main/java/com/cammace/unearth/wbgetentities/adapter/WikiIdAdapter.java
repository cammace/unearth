package com.cammace.unearth.wbgetentities.adapter;

import com.cammace.unearth.wbgetentities.model.Entity;
import com.cammace.unearth.wbgetentities.model.WikiId;
import com.squareup.moshi.FromJson;

import java.io.IOException;
import java.util.Map;

public class WikiIdAdapter {

  @FromJson
  public WikiId fromJson(Map<String, Entity> raw) throws IOException {
    return new WikiId((Entity) raw.values().toArray()[0]);
  }
}