package com.mapflow.geo.common.dao;

import org.junit.Test;

import com.mapflow.test.dao.BaseDaoTestCase;

public class RoleDaoTest extends BaseDaoTestCase {

  // @Autowired
  // private RoleDao dao;

  @Test
  public void testAddAndRemoveRole() throws Exception {
    // Role role = new Role("testrole");
    // role.setDescription("new role descr");
    // dao.save(role);
    // flush();
    //
    // role = dao.getRoleByName("testrole");
    // assertNotNull(role.getDescription());
    //
    // dao.removeRole("testrole");
    // flush();
    //
    // role = dao.getRoleByName("testrole");
    // assertNull(role);
  }

  @Test
  public void testFindByNamedQuery() {
    // final HashMap<String, Object> queryParams = new HashMap<String, Object>();
    // queryParams.put("name", Constants.USER_ROLE);
    // final List<Role> roles = dao.findByNamedQuery("findRoleByName", queryParams);
    // assertNotNull(roles);
    // assertTrue(roles.size() > 0);
  }

  @Test
  public void testGetRole() throws Exception {
    // final Role role = dao.getRoleByName(Constants.USER_ROLE);
    // assertNotNull(role);
  }

  @Test
  public void testGetRoleInvalid() throws Exception {
    // final Role role = dao.getRoleByName("badrolename");
    // assertNull(role);
  }

  @Test
  public void testUpdateRole() throws Exception {
    // Role role = dao.getRoleByName("ROLE_USER");
    // role.setDescription("test descr");
    // dao.save(role);
    // flush();
    //
    // role = dao.getRoleByName("ROLE_USER");
    // assertEquals("test descr", role.getDescription());
  }
}
