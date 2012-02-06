package com.mapflow.geo.common.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mapflow.geo.common.dao.UserDao;
import com.mapflow.geo.common.model.User;
import com.mapflow.geo.common.service.UserExistsException;
import com.mapflow.geo.common.service.UserManager;
import com.mapflow.geo.common.service.UserService;

/**
 * Implementation of UserManager interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("userManager")
@WebService(serviceName = "UserService", endpointInterface = "com.mapflow.geo.common.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager,
  UserService {

  private PasswordEncoder passwordEncoder;
  private UserDao userDao;

  /**
   * {@inheritDoc}
   */
  @Override
  public User getUser(final String userId) {
    return userDao.get(new Long(userId));
  }

  /**
   * {@inheritDoc}
   * 
   * @param username
   *          the login name of the human
   * @return User the populated user object
   * @throws UsernameNotFoundException
   *           thrown when username not found
   */
  @Override
  public User getUserByUsername(final String username) throws UsernameNotFoundException {
    return (User) userDao.loadUserByUsername(username);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<User> getUsers() {
    return userDao.getAllDistinct();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeUser(final String userId) {
    log.debug("removing user: " + userId);
    userDao.remove(new Long(userId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User saveUser(final User user) throws UserExistsException {

    if (user.getVersion() == null) {
      // if new user, lowercase userId
      user.setUsername(user.getUsername().toLowerCase());
    }

    // Get and prepare password management-related artifacts
    boolean passwordChanged = false;
    if (passwordEncoder != null) {
      // Check whether we have to encrypt (or re-encrypt) the password
      if (user.getVersion() == null) {
        // New user, always encrypt
        passwordChanged = true;
      }
      else {
        // Existing user, check password in DB
        final String currentPassword = userDao.getUserPassword(user.getUsername());
        if (currentPassword == null) {
          passwordChanged = true;
        }
        else {
          if (!currentPassword.equals(user.getPassword())) {
            passwordChanged = true;
          }
        }
      }

      // If password was changed (or new user), encrypt it
      if (passwordChanged) {
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
      }
    }
    else {
      log.warn("PasswordEncoder not set, skipping password encryption...");
    }

    try {
      return userDao.saveUser(user);
    }
    catch (final DataIntegrityViolationException e) {
      // e.printStackTrace();
      log.warn(e.getMessage());
      throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
    }
    catch (final JpaSystemException e) { // needed for JPA
      // e.printStackTrace();
      log.warn(e.getMessage());
      throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<User> search(final String searchTerm) {
    return super.search(searchTerm, User.class);
  }

  @Autowired
  public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Autowired
  public void setUserDao(final UserDao userDao) {
    dao = userDao;
    this.userDao = userDao;
  }
}
