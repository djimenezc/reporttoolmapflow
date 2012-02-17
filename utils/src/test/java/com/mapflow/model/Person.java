package com.mapflow.model;

import java.io.Serializable;

/**
 * Person example class. This bean is bind with a xml data representation.
 * 
 * @author djimenez
 */
public class Person implements Serializable {

  private static final long serialVersionUID = 301396521046927216L;
  private String name;
  private String address;
  private String ssn;
  private String email;
  private String homePhone;
  private String workPhone;

  // -- used by the data-binding framework
  public Person() {
  }

  // -- allows us to create a Person via the constructor
  public Person(final String name, final String address, final String ssn, final String email,
    final String homePhone, final String workPhone) {
    this.name = name;
    this.address = address;
    this.ssn = ssn;
    this.email = email;
    this.homePhone = homePhone;
    this.workPhone = workPhone;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Person other = (Person) obj;
    if (address == null) {
      if (other.address != null) {
        return false;
      }
    }
    else if (!address.equals(other.address)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    }
    else if (!email.equals(other.email)) {
      return false;
    }
    if (homePhone == null) {
      if (other.homePhone != null) {
        return false;
      }
    }
    else if (!homePhone.equals(other.homePhone)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    }
    else if (!name.equals(other.name)) {
      return false;
    }
    if (ssn == null) {
      if (other.ssn != null) {
        return false;
      }
    }
    else if (!ssn.equals(other.ssn)) {
      return false;
    }
    if (workPhone == null) {
      if (other.workPhone != null) {
        return false;
      }
    }
    else if (!workPhone.equals(other.workPhone)) {
      return false;
    }
    return true;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getHomePhone() {
    return homePhone;
  }

  // -- accessors
  public String getName() {
    return name;
  }

  public String getSsn() {
    return ssn;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((homePhone == null) ? 0 : homePhone.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
    result = prime * result + ((workPhone == null) ? 0 : workPhone.hashCode());
    return result;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setHomePhone(final String homePhone) {
    this.homePhone = homePhone;
  }

  // -- mutators
  public void setName(final String name) {
    this.name = name;
  }

  public void setSsn(final String ssn) {
    this.ssn = ssn;
  }

  public void setWorkPhone(final String workPhone) {
    this.workPhone = workPhone;
  }
}
