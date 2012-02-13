package com.mapflow.geo.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.compass.core.CompassCallbackWithoutResult;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.compass.gps.CompassGps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.ExpectedException;

import com.mapflow.geo.common.constants.Constants;
import com.mapflow.geo.common.model.Address;
import com.mapflow.geo.common.model.Role;
import com.mapflow.geo.common.model.entities.User;
import com.mapflow.test.dao.BaseDaoTestCase;

public class UserDaoTest extends BaseDaoTestCase {

  private static final String AAA_GMAIL_COM = "aaa@gmail.com";
  @Autowired
  private UserDao dao;
//  @Autowired
//  private RoleDao rdao;
  @Autowired
  private CompassTemplate compassTemplate;
  @Autowired
  private CompassGps compassGps;

  @Test
  @ExpectedException(DataAccessException.class)
  public void testAddAndRemoveUser() throws Exception {

    User user = new User("testuser");
    user.setPassword("testpass");
    user.setFirstName("Test");
    user.setLastName("Last");
    final Address address = new Address();
    address.setCity("Denver");
    address.setProvince("CO");
    address.setCountry("USA");
    address.setPostalCode("80210");
    user.setAddress(address);
    user.setEmail("testuser@appfuse.org");
    user.setWebsite("http://raibledesigns.com");

    final Role role = new Role(Constants.USER_ROLE);
//    assertNotNull(role.getId());
    user.addRole(role);

    user = dao.saveUser(user);
    flush();

    assertNotNull(user.getId());
    user = dao.get(user.getId());
    assertEquals("testpass", user.getPassword());

    dao.remove(user.getId());
    flush();

    // should throw DataAccessException
    dao.get(user.getId());
  }

  @Test
  public void testAddUserRole() throws Exception {
    User user = dao.get(1L);
    assertEquals(1, user.getRolesList().size());

    final Role role = new Role(Constants.ADMIN_ROLE);
    user.addRole(role);
    dao.saveUser(user);
    flush();

    user = dao.get(1L);
    assertEquals(2, user.getRolesList().size());

    // add the same role twice - should result in no additional role
    user.addRole(role);

    dao.saveUser(user);
    flush();

    user = dao.get(1L);
    assertEquals("more than 2 roles", 2, user.getRolesList().size());

    user.removeRole(role);
    dao.saveUser(user);
    flush();

    user = dao.get(1L);
    assertEquals(1, user.getRolesList().size());
  }

  @Test
  public void testGetUser() throws Exception {
    final User user = dao.get(1L);

    assertNotNull(user);
    assertEquals(1, user.getRolesList().size());
    assertTrue(user.isEnabled());
  }

  @Test
  @ExpectedException(DataAccessException.class)
  public void testGetUserInvalid() throws Exception {
    // should throw DataAccessException
    dao.get(1000L);
  }

  @Test
  public void testGetUserPassword() throws Exception {
    final User user = dao.get(1L);
    final String password = dao.getUserPassword(user.getUsername());
    assertNotNull(password);
    log.debug("password: " + password);
  }

  @Test
//  @NotTransactional
  @ExpectedException(DataIntegrityViolationException.class)
  public void testUpdateUser() throws Exception {
    User user = dao.get(1L);

    user.setEmail(AAA_GMAIL_COM);

    dao.saveUser(user);
    flush();

    user = dao.get(1L);
    assertEquals(AAA_GMAIL_COM, user.getEmail());

    // verify that violation occurs when adding new user with same username
    user.setId(null);

    // should throw DataIntegrityViolationException
    dao.saveUser(user);
  }

  @Test
  public void testUserExists() throws Exception {
    final boolean b = dao.exists(1L);
    assertTrue(b);
  }

  @Test
  public void testUserNotExists() throws Exception {
    final boolean b = dao.exists(111L);
    assertFalse(b);
  }

  @Test
  public void testUserSearch() throws Exception {
    // reindex all the data
    compassGps.index();

    User user = compassTemplate.get(User.class, 2);
    assertNotNull(user);
    assertEquals("Admin user", user.getComments());

    compassTemplate.execute(new CompassCallbackWithoutResult() {

      @Override
      protected void doInCompassWithoutResult(final CompassSession compassSession)
        throws CompassException {
        final CompassHits hits = compassSession.find("Admin");
        assertEquals(2, hits.length());
        assertEquals("Admin user", ((User) hits.data(0)).getComments());
        assertEquals("Admin user", hits.resource(0).getValue("comments"));
      }
    });

    // test mirroring
    user = dao.get(2L);
    user.setComments("MattX");
    dao.saveUser(user);
    flush();

    // now verify it is reflected in the index
    user = compassTemplate.get(User.class, 2);
    assertNotNull(user);
    assertEquals("MattX", user.getComments());

    compassTemplate.execute(new CompassCallbackWithoutResult() {

      @Override
      protected void doInCompassWithoutResult(final CompassSession compassSession)
        throws CompassException {
        final CompassHits hits = compassSession.find("MattX");
        assertEquals(1, hits.length());
        assertEquals("MattX", ((User) hits.data(0)).getComments());
      }
    });
  }
}
