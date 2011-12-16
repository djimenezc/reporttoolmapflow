package com.mapflow.util;

import java.util.Arrays;
import java.util.Collection;

public class StringTransformer {

  public static Collection<String> stringTocCollection(final String roles, final String separator) {

    final String[] stringArray = roles.split(separator);

    final Collection<String> list = Arrays.asList(stringArray);

    return list;
  }

}
