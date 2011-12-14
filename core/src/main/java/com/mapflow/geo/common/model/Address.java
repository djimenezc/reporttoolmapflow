package com.mapflow.geo.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;

/**
 * This class is used to represent an address with address, city, province and postal-code
 * information.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Embeddable
@Searchable(root = false)
public class Address extends BaseObject implements Serializable {

  private static final long serialVersionUID = 3617859655330969141L;
  private String address;
  private String city;
  private String province;
  private String country;
  private String postalCode;

  @Column(length = 150)
  @SearchableProperty
  public String getAddress() {
    return address;
  }

  @Column(length = 50)
  @SearchableProperty
  public String getCity() {
    return city;
  }

  @Column(length = 100)
  @SearchableProperty
  public String getProvince() {
    return province;
  }

  @Column(length = 100)
  @SearchableProperty
  public String getCountry() {
    return country;
  }

  @Column(name = "postal_code", length = 15)
  @SearchableProperty
  public String getPostalCode() {
    return postalCode;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public void setPostalCode(final String postalCode) {
    this.postalCode = postalCode;
  }

  public void setProvince(final String province) {
    this.province = province;
  }

  /**
   * Overridden equals method for object comparison. Compares based on hashCode.
   * 
   * @param o
   *          Object to compare
   * @return true/false based on hashCode
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Address)) {
      return false;
    }

    final Address address1 = (Address) o;

    return this.hashCode() == address1.hashCode();
  }

  /**
   * Overridden hashCode method - compares on address, city, province, country and postal code.
   * 
   * @return hashCode
   */
  @Override
  public int hashCode() {
    int result;
    result = (address != null ? address.hashCode() : 0);
    result = (29 * result) + (city != null ? city.hashCode() : 0);
    result = (29 * result) + (province != null ? province.hashCode() : 0);
    result = (29 * result) + (country != null ? country.hashCode() : 0);
    result = (29 * result) + (postalCode != null ? postalCode.hashCode() : 0);
    return result;
  }

  /**
   * Returns a multi-line String with key=value pairs.
   * 
   * @return a String representation of this class.
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("country", country)
      .append("address", address).append("province", province).append("postalCode", postalCode)
      .append("city", city).toString();
  }
}
