package org.appfuse.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.mapflow.geo.common.constants.Constants;
import com.mapflow.geo.common.model.LabelValue;
import com.mapflow.geo.common.model.Role;
import com.mapflow.geo.common.persistence.LookupDao;

public class LookupManagerImplTest extends BaseManagerMockTestCase {

  private final LookupManagerImpl mgr = new LookupManagerImpl();
  private LookupDao lookupDao;

  @Before
  public void setUp() throws Exception {
    lookupDao = context.mock(LookupDao.class);
    mgr.dao = lookupDao;
  }

  @Test
  public void testGetAllRoles() {
    log.debug("entered 'testGetAllRoles' method");

    // set expected behavior on dao
    final Role role = new Role(Constants.ADMIN_ROLE);
    final List<Role> testData = new ArrayList<Role>();
    testData.add(role);
    context.checking(new Expectations() {

      {
        one(lookupDao).getRoles();
        will(returnValue(testData));
      }
    });

    final List<LabelValue> roles = mgr.getAllRoles();
    assertTrue(roles.size() > 0);
  }
}
