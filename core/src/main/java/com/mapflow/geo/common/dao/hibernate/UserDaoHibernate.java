package com.mapflow.geo.common.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.mapflow.geo.common.dao.UserDao;
import com.mapflow.geo.common.model.entities.User;
import com.mapflow.geo.common.persistence.hibernate.GenericDaoHibernate;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and retrieve User objects.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by <a
 *         href="mailto:dan@getrolling.com">Dan Kibler</a> Extended to implement Acegi
 *         UserDetailsService interface by David Carter david@carter.net Modified by <a
 *         href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with the new BaseDaoHibernate
 *         implementation that uses generics.
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao,
  UserDetailsService {

  /**
   * Constructor that sets the entity to User.class.
   */
  public UserDaoHibernate() {
    super(User.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<User> getUsers() {
    return getHibernateTemplate().find("from User u order by upper(u.username)");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User saveUser(final User user) {

    if (log.isDebugEnabled()) {
      log.debug("user's id: " + user.getId());
    }
    try {
      getHibernateTemplate().saveOrUpdate(user);
      // necessary to throw a DataIntegrityViolation and catch it in UserManager
      getHibernateTemplate().flush();
    }
    catch (Exception e) {
      throw new DataIntegrityViolationException(e.getMessage());
    }
    return user;
  }

  /**
   * Overridden simply to call the saveUser method. This is happenening because saveUser flushes the
   * session and saveObject of BaseDaoHibernate does not.
   * 
   * @param user
   *          the user to save
   * @return the modified user (with a primary key set if they're new)
   */
  @Override
  public User save(final User user) {
    return this.saveUser(user);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

    @SuppressWarnings("unchecked")
    final List<User> users = getHibernateTemplate().find("from User where username=?", username);

    if ((users == null) || users.isEmpty()) {
      throw new UsernameNotFoundException("user '" + username + "' not found...");
    }
    else {
      return (UserDetails) users.get(0);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUserPassword(final String username) {
    final SimpleJdbcTemplate jdbcTemplate =
      new SimpleJdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
    final Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
    return jdbcTemplate.queryForObject(
      "select password from " + table.name() + " where username=?", String.class, username);

  }

}
