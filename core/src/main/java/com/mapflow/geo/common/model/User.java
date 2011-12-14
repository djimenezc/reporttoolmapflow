package com.mapflow.geo.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
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

/**
 * This class represents the basic "user" object in AppFuse that allows for authentication and user
 * management. It implements Acegi Security's UserDetails interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by Dan Kibler
 *         (dan@getrolling.com) Extended to implement Acegi UserDetails interface by David Carter
 *         david@carter.net
 */
@Entity
@Table(name = "USERS", schema = "GEOUW", uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
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
  private Address address;
  private String roles;

  private String clientId;
  private String comments;
  private Long dataId;
  private Date passwordExpiry;
  private Long userRoleId;
  private Date disabledDate;
  private Date lastLoginDate;
  private Date createdDate;
  private String roleName;
  private String emailAddress;
  private String pwSalt;
  private String branchName;

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

  public void addRole(final Role role) {

    roles += role.getName();
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

  public Address getAddress() {
    return address;
  }

  @Override
  @Transient
  public Collection<GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }

  @Column(name = "BRANCH_NAME", length = 20)
  public String getBranchName() {
    return branchName;
  }

  @Column(name = "CLIENT_ID", length = 20)
  public String getClientId() {
    return clientId;
  }

  @Column(name = "COMMENTS", length = 100)
  public String getComments() {
    return comments;
  }

  @Transient
  @XmlTransient
  public String getConfirmPassword() {
    return confirmPassword;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "CREATED_DATE", length = 7)
  public Date getCreatedDate() {
    return createdDate;
  }

  @Column(name = "DATA_ID", precision = 10, scale = 0)
  public Long getDataId() {
    return dataId;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "DISABLED_DATE", length = 7)
  public Date getDisabledDate() {
    return disabledDate;
  }

  @Column(nullable = false, unique = true)
  @SearchableProperty
  public String getEmail() {
    return email;
  }

  @Column(name = "EMAIL_ADDRESS", length = 320)
  public String getEmailAddress() {
    return emailAddress;
  }

  @Column(name = "first_name", nullable = false, length = 50)
  @SearchableProperty
  public String getFirstName() {
    return firstName;
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

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @SearchableId
  public Long getId() {
    return id;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "LAST_LOGIN_DATE", length = 7)
  public Date getLastLoginDate() {
    return lastLoginDate;
  }

  @Column(name = "last_name", nullable = false, length = 50)
  @SearchableProperty
  public String getLastName() {
    return lastName;
  }

  @Override
  @Column(name = "PASSWORD", length = 150, nullable = false)
  @XmlTransient
  public String getPassword() {
    return password;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "PASSWORD_EXPIRY", length = 7)
  public Date getPasswordExpiry() {
    return passwordExpiry;
  }

  @Column(name = "password_hint")
  @XmlTransient
  public String getPasswordHint() {
    return passwordHint;
  }

  @Column(name = "phone_number")
  @SearchableProperty
  public String getPhoneNumber() {
    return phoneNumber;
  }

  @Column(name = "PW_SALT", length = 10)
  public String getPwSalt() {
    return pwSalt;
  }

  @Column(name = "ROLE_NAME", length = 20)
  public String getRoleName() {
    return roleName;
  }

  @Transient
  public List<Role> getRolesList() {

    final String[] stringList = roles.split(",");

    final List<Role> rolesList = new ArrayList<Role>();

    Long i = 0L;

    for (final String roleName : stringList) {
      final Role role = new Role();
      role.setId(++i);
      role.setName(roleName);

      rolesList.add(role);
    }

    return rolesList;
  }

  @Override
  @Column(name = "USERNAME", unique = true, length = 40, nullable = false)
  @SearchableProperty
  public String getUsername() {
    return username;
  }

  @Column(name = "USER_ROLE_ID", precision = 10, scale = 0)
  public Long getUserRoleId() {
    return userRoleId;
  }

  // @ManyToMany(fetch = FetchType.EAGER)
  // @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") },
  // inverseJoinColumns = @JoinColumn(name = "role_id"))
  @Version
  public Integer getVersion() {
    return version;
  }

  @SearchableProperty
  public String getWebsite() {
    return website;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return (username != null ? username.hashCode() : 0);
  }

  @Column(name = "account_expired", nullable = false)
  public boolean isAccountExpired() {
    return accountExpired;
  }

  @Column(name = "account_locked", nullable = false)
  public boolean isAccountLocked() {
    return accountLocked;
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

  @Override
  @Column(name = "account_enabled")
  public boolean isEnabled() {
    return enabled;
  }

  public void setAccountExpired(final boolean accountExpired) {
    this.accountExpired = accountExpired;
  }

  public void setAccountLocked(final boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  public void setAddress(final Address address) {
    this.address = address;
  }

  public void setBranchName(final String branchName) {
    this.branchName = branchName;
  }

  public void setClientId(final String clientId) {
    this.clientId = clientId;
  }

  public void setComments(final String comments) {
    this.comments = comments;
  }

  public void setConfirmPassword(final String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public void setCreatedDate(final Date createdDate) {
    this.createdDate = createdDate;
  }

  public void setCredentialsExpired(final boolean credentialsExpired) {
    this.credentialsExpired = credentialsExpired;
  }

  public void setDataId(final Long dataId) {
    this.dataId = dataId;
  }

  public void setDisabledDate(final Date disabledDate) {
    this.disabledDate = disabledDate;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setEmailAddress(final String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void setEnabled(final boolean enabled) {
    this.enabled = enabled;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setLastLoginDate(final Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setPasswordExpiry(final Date passwordExpiry) {
    this.passwordExpiry = passwordExpiry;
  }

  public void setPasswordHint(final String passwordHint) {
    this.passwordHint = passwordHint;
  }

  public void setPhoneNumber(final String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setPwSalt(final String pwSalt) {
    this.pwSalt = pwSalt;
  }

  public void setRoleName(final String roleName) {
    this.roleName = roleName;
  }

  public void setRoles(final List<Role> roles) {

    final StringBuffer rolesStringBuffer = new StringBuffer();

    for (final Role role : roles) {
      rolesStringBuffer.append(role.getName());
    }

    this.roles = rolesStringBuffer.toString();
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setUserRoleId(final Long userRoleId) {
    this.userRoleId = userRoleId;
  }

  public void setVersion(final Integer version) {
    this.version = version;
  }

  public void setWebsite(final String website) {
    this.website = website;
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

    if (getRolesList() != null) {
      sb.append("Granted Authorities: ");

      int i = 0;
      for (final Role role : getRolesList()) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(role.toString());
        i++;
      }
    }
    else {
      sb.append("No Granted Authorities");
    }
    return sb.toString();
  }
}
