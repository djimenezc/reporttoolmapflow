package com.mapflow.geo.common.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapflow.geo.common.persistence.LookupDao;
import com.mapflow.test.dao.BaseDaoTestCase;

/**
 * This class tests the current LookupDao implementation class
 * 
 * @author mraible
 */
public class LookupDaoTest extends BaseDaoTestCase {

  @Autowired
  LookupDao lookupDao;

  @Test
  public void testGetRoles() {
    final List roles = lookupDao.getRoles();
    log.debug(roles);
    assertTrue(roles.size() > 0);
  }
}
