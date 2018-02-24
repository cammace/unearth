package com.cammace.unearth;

import com.cammace.unearth.wbgetentities.constants.PropOptions.Props;
import com.cammace.unearth.wbgetentities.model.WikidataResponse;
import com.cammace.unearth.wbgetentities.network.GetWikiDescriptionService;
import com.cammace.unearth.wbgetentities.network.RetrofitInstance;
import com.cammace.unearth.wbgetentities.utils.TextUtils;

import retrofit2.Call;

public class Unearth {

  private static final String WIKI_ACTION = "wbgetentities";
  private static final String FORMAT = "json";

  private final String itemId;
  private final String props;
  private GetWikiDescriptionService service;
  private Call<WikidataResponse> call;

  private Unearth(String itemId, String props) {
    this.itemId = itemId;
    this.props = props;
    initialize();
  }

  private void initialize() {
    service = RetrofitInstance.getRetrofitInstance().create(GetWikiDescriptionService.class);
  }

  public Call<WikidataResponse> getCall() {
    // No need to recreate it
    if (call != null) {
      return call;
    }

    call = service.getCall(
      WIKI_ACTION,
      FORMAT,
      itemId,
      props,
      "en"
    );

    // Done
    return call;
  }


  public static class Builder {

    private String itemId;
    private String props;

    public Builder item(String itemId) {
      this.itemId = itemId;
      return this;
    }

    public Builder languages() {
      return this;
    }

    public Builder props(@Props String... props) {
      this.props = TextUtils.join("|", props);
      return this;
    }

    public Unearth build() {
      return new Unearth(itemId, props);
    }
  }
}