package com.cammace.unearth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cammace.unearth.wbgetentities.constants.PropOptions;
import com.cammace.unearth.wbgetentities.model.WikiId;
import com.cammace.unearth.wbgetentities.model.WikidataResponse;
import com.cammace.unearth.wbgetentities.utils.HashUtils;
import com.mapbox.geocoding.v5.models.CarmenFeature;
import com.mapbox.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.plugins.places.autocomplete.model.PlaceOptions;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements Callback<WikidataResponse> {

  private static final int REQUEST_ID = 5678;

  private TextView descriptionTextView;
  private ImageView placeImage;
  private TextView websiteTextView;
  private FloatingActionButton placeSearchButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    placeSearchButton = findViewById(R.id.button_place_search);
    descriptionTextView = findViewById(R.id.description);
    websiteTextView = findViewById(R.id.website_tv);
    placeImage = findViewById(R.id.imageView);

    placeSearchButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new PlaceAutocomplete.IntentBuilder()
          .accessToken("pk.eyJ1IjoiY2FtbWFjZSIsImEiOiI5OGQxZjRmZGQ2YjU3Mzk1YjJmZTQ5ZDY2MTg1NDJiOCJ9.hIFoCKGAGOwQkKyVPvrxvQ")
          .placeOptions(PlaceOptions.builder()
            .backgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary))
            .build(PlaceOptions.MODE_CARDS))
          .build(MainActivity.this);
        startActivityForResult(intent, REQUEST_ID);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_ID) {
      CarmenFeature feature = PlaceAutocomplete.getPlace(data);
      requestWikiData(feature);
    }
  }

  private void requestWikiData(CarmenFeature feature) {
    if (!feature.properties().has("wikidata")) {
      Toast.makeText(this, "No Wikidata provided in geocoder result", Toast.LENGTH_LONG).show();
    }
    String wikiData = feature.properties().get("wikidata").getAsString();

    Unearth unearth = new Unearth.Builder()
      .item(wikiData)
      .props(PropOptions.DESCRIPTIONS, PropOptions.CLAIMS)
      .build();
    unearth.getCall().enqueue(this);
  }

  @Override
  public void onResponse(@NonNull Call<WikidataResponse> call,
                         @NonNull Response<WikidataResponse> response) {
    // Print URL for easier debugging
    Timber.v("Request Url: %s", call.request().url().toString());

    WikiId entities = response.body().getEntities();
    if (entities == null) {
      return;
    }

    // Set place information strings
    descriptionTextView.setText(getDescription(entities));
    websiteTextView.setText(getWebsite(entities));

    // Set the imageView if a image Url is avaliable.
    String imageUrl = getImage(entities);
    if (imageUrl != null) {
      placeImage.setVisibility(View.VISIBLE);
      Picasso.with(this).load(imageUrl).into(placeImage);
    } else {
      placeImage.setVisibility(View.INVISIBLE);
    }
  }

  @Override
  public void onFailure(@NonNull Call<WikidataResponse> call, @NonNull Throwable throwable) {
    Timber.e(throwable);
  }

  @NonNull
  private String getDescription(WikiId entities) {
    if (entities.getEntity().getDescriptions() != null) {
      return entities.getEntity().getDescriptions().getLanguage().getValue();
    }
    return "";
  }

  @NonNull
  private String getWebsite(WikiId entities) {
    if (entities.getEntity().getClaims() != null
      && entities.getEntity().getClaims().getWebsite() != null
      && !entities.getEntity().getClaims().getWebsite().isEmpty()) {
      return entities.getEntity().getClaims().getWebsite().get(0).getMainsnak().getDatavalue().getValue();
    }
    return "";
  }

  @Nullable
  private String getImage(WikiId entities) {
    if (entities.getEntity().getClaims() != null
      && entities.getEntity().getClaims().getImage() != null
      && !entities.getEntity().getClaims().getImage().isEmpty()) {
      String imageUrl
        = entities.getEntity().getClaims().getImage().get(0).getMainsnak().getDatavalue().getValue();
      imageUrl = imageUrl.replace(" ", "_");
      String hash = HashUtils.generateHash(imageUrl).substring(0, 2);

      return "https://upload.wikimedia.org/wikipedia/commons/"
        + hash.substring(0, 1) + "/" + hash + "/" + imageUrl;
    }
    return null;
  }
}
