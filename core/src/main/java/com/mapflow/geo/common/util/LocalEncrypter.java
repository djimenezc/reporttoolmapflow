package com.mapflow.geo.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

public class LocalEncrypter {

  private static String algorithm = "DES";
  private static String characterSet = "ASCII";
  private static Key key = null;
  private static Cipher cipher = null;

  private static LocalEncrypter instance = null;

  protected LocalEncrypter() {
  }

  public static LocalEncrypter getInstance(final String key) {
    if (instance == null) {
      instance = new LocalEncrypter();
      try {
        instance.setUp(key);
      }
      catch (final Exception e) {
        e.printStackTrace();
      }
    }
    return instance;
  }

  protected void setUp(final String p_key) throws Exception {
    key = new SecretKeySpec(p_key.getBytes(), algorithm);
    cipher = Cipher.getInstance(algorithm);
  }

  public String convertByteArrayToString(final byte[] in) {
    return new String(in);
  }

  public String encryptForUrl(final String str) throws InvalidKeyException,
    UnsupportedEncodingException {
    final String encrypted = encrypt(str);
    return URLEncoder.encode(encrypted, characterSet);
  }

  @SuppressWarnings("restriction")
  public String encrypt(final String str) throws InvalidKeyException {
    cipher.init(Cipher.ENCRYPT_MODE, key);
    try {
      // Encode the string into bytes using utf-8
      final byte[] utf8 = str.getBytes(characterSet);

      // Encrypt
      final byte[] enc = cipher.doFinal(utf8);

      // Encode bytes to base64 to get a string
      return new sun.misc.BASE64Encoder().encode(enc);
    }
    catch (final javax.crypto.BadPaddingException e) {
    }
    catch (final IllegalBlockSizeException e) {
    }
    catch (final UnsupportedEncodingException e) {
    }
    return null;
  }

  public String decrypt(final String str) throws InvalidKeyException {
    cipher.init(Cipher.DECRYPT_MODE, key);
    try {
      // Decode base64 to get bytes
      @SuppressWarnings("restriction")
      final byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

      // Decrypt
      final byte[] utf8 = cipher.doFinal(dec);

      // Decode using utf-8
      return new String(utf8, characterSet);
    }
    catch (final javax.crypto.BadPaddingException e) {
    }
    catch (final IllegalBlockSizeException e) {
    }
    catch (final UnsupportedEncodingException e) {
    }
    catch (final java.io.IOException e) {
    }
    return null;
  }
}
