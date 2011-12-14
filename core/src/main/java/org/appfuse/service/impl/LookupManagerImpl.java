package org.appfuse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.LookupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapflow.geo.common.model.LabelValue;
import com.mapflow.geo.common.persistence.LookupDao;

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

    // List<Role> roles = dao.getRoles();
    final List<LabelValue> list = new ArrayList<LabelValue>();

    // for (Role role1 : roles) {
    // list.add(new LabelValue(role1.getName(), role1.getName()));
    // }

    return list;
  }
}
