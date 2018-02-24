package com.cammace.unearth.wbgetentities.model;

import com.cammace.unearth.Unearth;
import com.squareup.moshi.Json;

public class WikiId {

  private Entity entity;

  public WikiId(Entity entity) {
    this.entity = entity;
  }

  public Entity getEntity() {
    return entity;
  }
}

