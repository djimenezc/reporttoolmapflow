package com.mapflow.geo.common.dao.hibernate;

import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapflow.test.dao.BaseDaoTestCase;

public class HibernateConfigurationTest extends BaseDaoTestCase {

  @Autowired
  SessionFactory sessionFactory;

  @Test
  public void testColumnMapping() throws Exception {
    final Session session = sessionFactory.openSession();
    try {
      final Map metadata = sessionFactory.getAllClassMetadata();
      for (final Object o : metadata.values()) {
        final EntityPersister persister = (EntityPersister) o;
        final String className = persister.getEntityName();
        log.debug("Trying select * from: " + className);
        final Query q = session.createQuery("from " + className + " c");
        q.iterate();
        log.debug("ok: " + className);
      }
    }
    finally {
      session.close();
    }
  }
}
