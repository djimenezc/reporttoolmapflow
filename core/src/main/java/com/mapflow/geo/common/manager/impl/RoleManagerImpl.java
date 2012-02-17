package com.mapflow.geo.common.manager.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapflow.geo.common.dao.RoleDao;
import com.mapflow.geo.common.manager.RoleManager;
import com.mapflow.geo.common.model.Role;

/**
 * Implementation of RoleManager interface.
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, Long> implements RoleManager {

  // RoleDao roleDao;
  private Map<Long, Role> roles;

  @Autowired
  public RoleManagerImpl(final RoleDao roleDao) {
    super(roleDao);
    // this.roleDao = roleDao;
    roles = new HashMap<Long, Role>();

    Role role1 = new Role("admin");
    roles.put(1L, role1);

    Role role2 = new Role("all");
    roles.put(2L, role2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Role> getRoles(final Role role) {
    return dao.getAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Role getRole(final String rolename) {

    Iterator<Entry<Long, Role>> itr = roles.entrySet().iterator();

    Role role = null;

    while (itr.hasNext()) {

      final Map.Entry<Long, Role> e = itr.next();

      if (e.getValue().getName().equals(rolename)) {
        role = e.getValue();
      }
    }

    return role;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Role saveRole(final Role role) {

    roles.put(role.getId(), role);

    return role;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeRole(final String rolename) {

    Iterator<Entry<Long, Role>> itr = roles.entrySet().iterator();

    while (itr.hasNext()) {

      final Map.Entry<Long, Role> e = itr.next();

      if (e.getValue().getName().equals(rolename)) {
        roles.remove(e.getKey());
      }
    }
  }
}
