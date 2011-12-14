package org.appfuse.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapflow.geo.common.persistence.LookupDao;

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
