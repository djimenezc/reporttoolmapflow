package org.appfuse.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapflow.geo.common.model.User;
import com.mapflow.geo.common.persistence.GenericDao;
import com.mapflow.geo.common.persistence.hibernate.GenericDaoHibernate;

public class GenericDaoTest extends BaseDaoTestCase {

  Log log = LogFactory.getLog(GenericDaoTest.class);
  GenericDao<User, Long> genericDao;
  @Autowired
  SessionFactory sessionFactory;

  @Before
  public void setUp() {
    genericDao = new GenericDaoHibernate<User, Long>(User.class, sessionFactory);
  }

  @Test
  public void getUser() {
    final User user = genericDao.get(-1L);
    assertNotNull(user);
    assertEquals("user", user.getUsername());
  }
}
