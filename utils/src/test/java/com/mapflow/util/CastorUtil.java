package com.mapflow.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
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

  public static <T> int writeObject(final T object, final String destinationXML) {

    FileWriter file;
    int result = -1;

    try {

      file = new FileWriter("target/bob_person.xml");
      Marshaller.marshal(object, file);
      file.close();
      result = 1;
    }
    catch (final IOException e) {

      return -1;
    }
    catch (final MarshalException e) {
      return -2;
    }
    catch (final ValidationException e) {
      return -3;
    }

    return result;
  }

}
