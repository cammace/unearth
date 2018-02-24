package com.cammace.unearth.wbgetentities.network;

import com.cammace.unearth.wbgetentities.model.WikidataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetWikiDescriptionService {
  @GET("api.php")
  Call<WikidataResponse> getCall(
    @Query("action") String action,
    @Query("format") String format,
    @Query("ids") String ids,
    @Query("props") String props,
    @Query("languages") String languages);
}