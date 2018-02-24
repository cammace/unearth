package com.cammace.unearth.wbgetentities.utils;

public final class TextUtils {

  public static String join(CharSequence delimiter, Object[] tokens) {
    if (tokens == null || tokens.length < 1) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    boolean firstTime = true;
    for (Object token : tokens) {
      if (firstTime) {
        firstTime = false;
      } else {
        sb.append(delimiter);
      }
      sb.append(token);
    }
    return sb.toString();
  }







}
