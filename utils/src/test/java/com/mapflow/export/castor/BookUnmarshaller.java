package com.mapflow.export.castor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.junit.Test;

import com.mapflow.model.Author;
import com.mapflow.model.Book;

public class BookUnmarshaller {

  @Test
  public void testUnmarshaller() throws FileNotFoundException, MarshalException,
    ValidationException {
    FileReader reader = new FileReader("target/book.xml");
    Book book = (Book) Unmarshaller.unmarshal(Book.class, reader);
    System.out.println("Book ISBN: " + book.getIsbn());
    System.out.println("Book Title: " + book.getTitle());
    List<Author> authors = book.getAuthors();
    for (Iterator<Author> i = authors.iterator(); i.hasNext();) {
      Author author = (Author) i.next();
      System.out.println("Author: " + author.getFirstName() + " " + author.getLastName());
    }

    System.out.println();

    reader = new FileReader("target/book2.xml");
    book = (Book) Unmarshaller.unmarshal(Book.class, reader);
    System.out.println("Book ISBN: " + book.getIsbn());
    System.out.println("Book Title: " + book.getTitle());
    authors = book.getAuthors();
    for (Iterator<Author> i = authors.iterator(); i.hasNext();) {
      Author author = (Author) i.next();
      System.out.println("Author: " + author.getFirstName() + " " + author.getLastName());
    }
  }
}
