package com.cammace.unearth.wbgetentities.network;

import com.cammace.unearth.wbgetentities.adapter.WikiIdAdapter;
import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitInstance {

  private static Retrofit retrofit;
  private static final String BASE_URL = "https://www.wikidata.org/w/";

  public static Retrofit getRetrofitInstance() {

    Moshi moshi = new Moshi.Builder()
      .add(new WikiIdAdapter())
      .build();

    if (retrofit == null) {
      retrofit = new retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build();
    }
    return retrofit;
  }
}