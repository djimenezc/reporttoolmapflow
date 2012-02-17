package com.mapflow.geo.common.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mapflow.geo.common.dao.LookupDao;
import com.mapflow.geo.common.model.Role;

/**
 * Hibernate implementation of LookupDao.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Repository
public class LookupDaoHibernate implements LookupDao {

  private final Log log = LogFactory.getLog(LookupDaoHibernate.class);
  // private final HibernateTemplate hibernateTemplate;
  private List<Role> roles;

  public LookupDaoHibernate() {

    roles = new ArrayList<Role>();

    roles.add(new Role("admin"));
    roles.add(new Role("all"));
  }

  /**
   * Initialize LookupDaoHibernate with Hibernate SessionFactory.
   * 
   * @param sessionFactory
   */
  // @Autowired
  // public LookupDaoHibernate(final SessionFactory sessionFactory) {
  // hibernateTemplate = new HibernateTemplate(sessionFactory);
  // }

  /**
   * {@inheritDoc}
   */
  public List<Role> getRoles() {
    log.debug("Retrieving all role names...");

    // return hibernateTemplate.find("from Role order by name");
    return roles;
  }
}
