package com.mapflow.geo.common.constants;
// Generated 14-Dec-2011 14:53:31 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name="USERS"
    ,schema="GEOUW"
    , uniqueConstraints = @UniqueConstraint(columnNames="USERNAME") 
)
public class Users  implements java.io.Serializable {


     private BigDecimal id;
     private String username;
     private String password;
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

    public Users() {
    }

	
    public Users(BigDecimal id) {
        this.id = id;
    }
    public Users(BigDecimal id, String username, String password, String clientId, String comments, Long dataId, Date passwordExpiry, Long userRoleId, Date disabledDate, Date lastLoginDate, Date createdDate, String roleName, String emailAddress, String pwSalt, String branchName) {
       this.id = id;
       this.username = username;
       this.password = password;
       this.clientId = clientId;
       this.comments = comments;
       this.dataId = dataId;
       this.passwordExpiry = passwordExpiry;
       this.userRoleId = userRoleId;
       this.disabledDate = disabledDate;
       this.lastLoginDate = lastLoginDate;
       this.createdDate = createdDate;
       this.roleName = roleName;
       this.emailAddress = emailAddress;
       this.pwSalt = pwSalt;
       this.branchName = branchName;
    }
   
     @Id 
    
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    @Column(name="USERNAME", unique=true, length=40)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="PASSWORD", length=150)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="CLIENT_ID", length=20)
    public String getClientId() {
        return this.clientId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    @Column(name="COMMENTS", length=100)
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    @Column(name="DATA_ID", precision=10, scale=0)
    public Long getDataId() {
        return this.dataId;
    }
    
    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="PASSWORD_EXPIRY", length=7)
    public Date getPasswordExpiry() {
        return this.passwordExpiry;
    }
    
    public void setPasswordExpiry(Date passwordExpiry) {
        this.passwordExpiry = passwordExpiry;
    }
    
    @Column(name="USER_ROLE_ID", precision=10, scale=0)
    public Long getUserRoleId() {
        return this.userRoleId;
    }
    
    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DISABLED_DATE", length=7)
    public Date getDisabledDate() {
        return this.disabledDate;
    }
    
    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_LOGIN_DATE", length=7)
    public Date getLastLoginDate() {
        return this.lastLoginDate;
    }
    
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATED_DATE", length=7)
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @Column(name="ROLE_NAME", length=20)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Column(name="EMAIL_ADDRESS", length=320)
    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    @Column(name="PW_SALT", length=10)
    public String getPwSalt() {
        return this.pwSalt;
    }
    
    public void setPwSalt(String pwSalt) {
        this.pwSalt = pwSalt;
    }
    
    @Column(name="BRANCH_NAME", length=20)
    public String getBranchName() {
        return this.branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }




}


