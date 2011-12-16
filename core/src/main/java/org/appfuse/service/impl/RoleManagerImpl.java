package org.appfuse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapflow.geo.common.dao.RoleDao;
import com.mapflow.geo.common.model.Role;
import com.mapflow.geo.common.service.RoleManager;

/**
 * Implementation of RoleManager interface.
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, Long> implements RoleManager {

  RoleDao roleDao;

  @Autowired
  public RoleManagerImpl(final RoleDao roleDao) {
    super(roleDao);
    this.roleDao = roleDao;
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
    return roleDao.getRoleByName(rolename);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Role saveRole(final Role role) {
    return dao.save(role);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeRole(final String rolename) {
    roleDao.removeRole(rolename);
  }
}
