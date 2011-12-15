package com.mapflow.test.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

public class CastorUtil {

  @SuppressWarnings("unchecked")
  public static <T> T readObject(T obj, final String fromXML) {

    try {
      final Reader reader = new FileReader(fromXML);

      obj = (T) Unmarshaller.unmarshal(obj.getClass(), reader);
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
    catch (final ValidationException e) {
      e.printStackTrace();
    }
    catch (final MarshalException e) {
      e.printStackTrace();
    }

    return obj;
  }
}
