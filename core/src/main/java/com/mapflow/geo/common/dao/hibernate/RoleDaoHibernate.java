package com.mapflow.geo.common.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mapflow.geo.common.dao.RoleDao;
import com.mapflow.geo.common.model.Role;
import com.mapflow.geo.common.persistence.hibernate.GenericDaoHibernate;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and retrieve Role objects.
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 */
@Repository
public class RoleDaoHibernate extends GenericDaoHibernate<Role, Long> implements RoleDao {

  /**
   * Constructor to create a Generics-based version using Role as the entity
   */
  public RoleDaoHibernate() {
    super(Role.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Role getRoleByName(final String rolename) {
    final List roles = getHibernateTemplate().find("from Role where name=?", rolename);
    if (roles.isEmpty()) {
      return null;
    }
    else {
      return (Role) roles.get(0);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeRole(final String rolename) {
    final Object role = getRoleByName(rolename);
    getHibernateTemplate().delete(role);
  }
}
