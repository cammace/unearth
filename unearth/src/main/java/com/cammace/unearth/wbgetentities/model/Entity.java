package com.cammace.unearth.wbgetentities.model;

public class Entity {

  private String type;
  private String id;
  private Descriptions descriptions;
  private Claim claims;

  private Entity(String type, String id, Descriptions descriptions, Claim claims) {
    this.descriptions = descriptions;
    this.claims = claims;
    this.type = type;
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public Descriptions getDescriptions() {
    return descriptions;
  }

  public Claim getClaims() {
    return claims;
  }

  @Override
  public String toString() {
    return "Entity{" +
      "type='" + type + '\'' +
      ", id='" + id + '\'' +
      ", descriptions=" + descriptions +
      ", claims=" + claims +
      '}';
  }

  public static class Builder {

    private String type;
    private String id;
    private Descriptions descriptions;
    private Claim claims;

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder descriptions(Descriptions descriptions) {
      this.descriptions = descriptions;
      return this;
    }

    public Builder claims(Claim claim) {
      this.claims = claim;
      return this;
    }

    public Entity build() {
      return new Entity(type, id, descriptions, claims);
    }
  }
}
