package com.cammace.unearth.wbgetentities.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class HashUtils {


  private HashUtils() {
    // No Instances
  }

  public static String generateHash(String phrase) {
    String hash = null;
    MessageDigest mdEnc;
    try {
      mdEnc = MessageDigest.getInstance("MD5");
      mdEnc.update(phrase.getBytes(), 0, phrase.length());
      phrase = new BigInteger(1, mdEnc.digest()).toString(16);
      while (phrase.length() < 32) {
        phrase = "0" + phrase;
      }
      hash = phrase;
    } catch (NoSuchAlgorithmException e1) {
      e1.printStackTrace();
    }
    return hash;
  }
}
