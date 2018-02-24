package com.cammace.unearth.wbgetentities.model;

/**
 * Response container class used when a API request is made and a successful request occurs.
 *
 * @since 0.1.0
 */
public class WikidataResponse {

  private WikiId entities;
  private int success;

  public int getSuccess() {
    return success;
  }

  public WikiId getEntities() {
    return entities;
  }
}