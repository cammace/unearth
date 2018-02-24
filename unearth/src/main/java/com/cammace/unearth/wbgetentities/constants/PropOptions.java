package com.cammace.unearth.wbgetentities.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PropOptions {

  private PropOptions() {
    // No Instances
  }

  public static final String INFO = "info";

  public static final String SITELINKS = "sitelinks";

  public static final String LABELS = "labels";

  public static final String DESCRIPTIONS = "descriptions";

  public static final String CLAIMS = "claims";

  @StringDef( {INFO, SITELINKS, LABELS, DESCRIPTIONS, CLAIMS})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Props {
  }
}
