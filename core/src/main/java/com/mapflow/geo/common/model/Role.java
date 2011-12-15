package com.mapflow.geo.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;

import com.mapflow.model.BaseObject;

/**
 * This class is used to represent available roles in the database.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Version by Dan Kibler
 *         dan@getrolling.com Extended to implement Acegi GrantedAuthority interface by David Carter
 *         david@carter.net
 */
@Entity
@Table(name = "role")
@NamedQueries({ @NamedQuery(name = "findRoleByName", query = "select r from Role r where r.name = :name ") })
public class Role extends BaseObject implements Serializable, GrantedAuthority {

  private static final long serialVersionUID = 3690197650654049848L;
  private Long id;
  private String name;
  private String description;

  /**
   * Default constructor - creates a new instance with no values set.
   */
  public Role() {
  }

  /**
   * Create a new instance and set the name.
   * 
   * @param name
   *          name of the role.
   */
  public Role(final String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  public int compareTo(final Object o) {
    return (equals(o) ? 0 : -1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Role)) {
      return false;
    }

    final Role role = (Role) o;

    return !(name != null ? !name.equals(role.name) : role.name != null);

  }

  /**
   * @return the name property (getAuthority required by Acegi's GrantedAuthority interface)
   * @see org.springframework.security.core.GrantedAuthority#getAuthority()
   */
  @Override
  @Transient
  public String getAuthority() {
    return getName();
  }

  @Column(length = 64)
  public String getDescription() {
    return description;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  @Column(length = 20)
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return (name != null ? name.hashCode() : 0);
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(name).toString();
  }
}
