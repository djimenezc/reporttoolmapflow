package com.mapflow.model;

import java.util.LinkedList;
import java.util.List;

public class Book {

  /** The book's ISBN */
  private String isbn;
  /** The book's title */
  private String title;
  /** The authors' names */
  private List<Author> authors;

  public Book() {
  }

  public Book(String isbn, String title, List<Author> authors) {
    this.isbn = isbn;
    this.title = title;
    this.authors = authors;
  }

  public Book(String isbn, String title, Author author) {
    this.isbn = isbn;
    this.title = title;
    this.authors = new LinkedList<Author>();
    authors.add(author);
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void addAuthor(Author author) {
    authors.add(author);
  }
}
