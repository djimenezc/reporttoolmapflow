package com.mapflow.model;

public class Author {

  private String firstName, lastName;
  private int totalSales;

  public Author() {
  }

  public Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setTotalSales(int totalSales) {
    this.totalSales = totalSales;
  }

  public void addToSales(int additionalSales) {
    this.totalSales += additionalSales;
  }

  public int getTotalSales() {
    return totalSales;
  }
}
