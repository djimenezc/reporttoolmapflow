package com.mapflow.geo.common.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mapflow.geo.common.model.BaseObject;

/**
 * This class represents the basic "user" object in AppFuse that allows for authentication and user
 * management. It implements Acegi Security's UserDetails interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by Dan Kibler
 *         (dan@getrolling.com) Extended to implement Acegi UserDetails interface by David Carter
 *         david@carter.net
 */
@Entity
@Table(name = "users")
@Searchable
@XmlRootElement
public class User extends BaseObject implements Serializable, UserDetails {

  private static final long serialVersionUID = 3832626162173359411L;

  private Long id;
  private String username; // required
  private String password; // required
  private String confirmPassword;
  private String passwordHint;
  private String firstName; // required
  private String lastName; // required
  private String email; // required; unique
  private String phoneNumber;
  private String website;
  private Integer version;
  private boolean enabled;
  private boolean accountExpired;
  private boolean accountLocked;
  private boolean credentialsExpired;

  /**
   * Default constructor - creates a new instance with no values set.
   */
  public User() {
  }

  /**
   * Create a new instance and set the username.
   * 
   * @param username
   *          login name for user.
   */
  public User(final String username) {
    this.username = username;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @SearchableId
  public Long getId() {
    return id;
  }

  @Override
  @Column(nullable = false, length = 50, unique = true)
  @SearchableProperty
  public String getUsername() {
    return username;
  }

  @Override
  @Column(nullable = false)
  @XmlTransient
  public String getPassword() {
    return password;
  }

  @Transient
  @XmlTransient
  public String getConfirmPassword() {
    return confirmPassword;
  }

  @Column(name = "password_hint")
  @XmlTransient
  public String getPasswordHint() {
    return passwordHint;
  }

  @Column(name = "first_name", nullable = false, length = 50)
  @SearchableProperty
  public String getFirstName() {
    return firstName;
  }

  @Column(name = "last_name", nullable = false, length = 50)
  @SearchableProperty
  public String getLastName() {
    return lastName;
  }

  @Column(nullable = false, unique = true)
  @SearchableProperty
  public String getEmail() {
    return email;
  }

  @Column(name = "phone_number")
  @SearchableProperty
  public String getPhoneNumber() {
    return phoneNumber;
  }

  @SearchableProperty
  public String getWebsite() {
    return website;
  }

  /**
   * Returns the full name.
   * 
   * @return firstName + ' ' + lastName
   */
  @Transient
  public String getFullName() {
    return firstName + ' ' + lastName;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = @JoinColumn(name = "role_id"))
  @Version
  public Integer getVersion() {
    return version;
  }

  @Override
  @Column(name = "account_enabled")
  public boolean isEnabled() {
    return enabled;
  }

  @Column(name = "account_expired", nullable = false)
  public boolean isAccountExpired() {
    return accountExpired;
  }

  /**
   * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
   * @return true if account is still active
   */
  @Override
  @Transient
  public boolean isAccountNonExpired() {
    return !isAccountExpired();
  }

  @Column(name = "account_locked", nullable = false)
  public boolean isAccountLocked() {
    return accountLocked;
  }

  /**
   * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
   * @return false if account is locked
   */
  @Override
  @Transient
  public boolean isAccountNonLocked() {
    return !isAccountLocked();
  }

  @Column(name = "credentials_expired", nullable = false)
  public boolean isCredentialsExpired() {
    return credentialsExpired;
  }

  /**
   * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
   * @return true if credentials haven't expired
   */
  @Override
  @Transient
  public boolean isCredentialsNonExpired() {
    return !credentialsExpired;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setConfirmPassword(final String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public void setPasswordHint(final String passwordHint) {
    this.passwordHint = passwordHint;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setPhoneNumber(final String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setWebsite(final String website) {
    this.website = website;
  }

  public void setVersion(final Integer version) {
    this.version = version;
  }

  public void setEnabled(final boolean enabled) {
    this.enabled = enabled;
  }

  public void setAccountExpired(final boolean accountExpired) {
    this.accountExpired = accountExpired;
  }

  public void setAccountLocked(final boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  public void setCredentialsExpired(final boolean credentialsExpired) {
    this.credentialsExpired = credentialsExpired;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }

    final User user = (User) o;

    return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return (username != null ? username.hashCode() : 0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    final ToStringBuilder sb =
      new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("username", username)
        .append("enabled", enabled).append("accountExpired", accountExpired)
        .append("credentialsExpired", credentialsExpired).append("accountLocked", accountLocked);

    // TODO add roles toString
    // if (roles != null) {
    // sb.append("Granted Authorities: ");
    //
    // int i = 0;
    // for (Role role : roles) {
    // if (i > 0) {
    // sb.append(", ");
    // }
    // sb.append(role.toString());
    // i++;
    // }
    // } else {
    // sb.append("No Granted Authorities");
    // }
    return sb.toString();
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }
}
