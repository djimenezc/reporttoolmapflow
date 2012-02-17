package com.mapflow.export.castor;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.exolab.castor.xml.Marshaller;
import org.junit.Test;

import com.mapflow.model.Author;
import com.mapflow.model.Book;

public class BookMarshallerTest {

  @Test
  public void testMarshaller() {
    try {
      Author finder = new Author("Joseph", "Finder");
      Book book = new Book("9780312347482", "Power Play", finder);
      FileWriter writer = new FileWriter("target/book.xml");
      Marshaller.marshal(book, writer);

      List<Author> book2Authors = new ArrayList<Author>();
      book2Authors.add(new Author("Douglas", "Preston"));
      book2Authors.add(new Author("Lincoln", "Child"));
      Book book2 = new Book("9780446618502", "The Book of the Dead", book2Authors);
      writer = new FileWriter("target/book2.xml");
      Marshaller.marshal(book2, writer);

    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
    }
  }
}
