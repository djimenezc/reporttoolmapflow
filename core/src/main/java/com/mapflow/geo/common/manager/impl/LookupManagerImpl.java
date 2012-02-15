package com.mapflow.geo.common.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapflow.geo.common.dao.LookupDao;
import com.mapflow.geo.common.manager.LookupManager;
import com.mapflow.geo.common.model.Role;
import com.mapflow.model.LabelValue;

/**
 * Implementation of LookupManager interface to talk to the persistence layer.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("lookupManager")
public class LookupManagerImpl implements LookupManager {

  @Autowired
  LookupDao dao;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<LabelValue> getAllRoles() {

    // final List<Role> roles = dao.getRoles();

    final List<Role> roles = new ArrayList<Role>();

    final Role role1 = new Role();
    role1.setId(1L);
    role1.setName("all");

    final Role role2 = new Role();
    role2.setId(1L);
    role2.setName("admin");

    roles.add(role1);
    roles.add(role2);

    final List<LabelValue> list = new ArrayList<LabelValue>();

    for (final Role roleAux : roles) {
      list.add(new LabelValue(roleAux.getName(), roleAux.getName()));
    }

    return list;
  }
}
