package com.mapflow.geo.logging.dao.impl;

import org.springframework.stereotype.Repository;

import com.mapflow.geo.common.persistence.hibernate.GenericDaoHibernate;
import com.mapflow.geo.logging.dao.LogMapdisplayDao;
import com.mapflow.geo.logging.model.LogMapdisplayTo;

/**
 * DAO to manage the CRUD operation with Log entries
 * 
 * @author djimenez
 */
@Repository("logMapdisplayDao")
public class LogMapdisplayHibernateDao extends GenericDaoHibernate<LogMapdisplayTo, String>
  implements LogMapdisplayDao {

  public LogMapdisplayHibernateDao() {
    super(LogMapdisplayTo.class);
  }

  public LogMapdisplayHibernateDao(final Class<LogMapdisplayTo> persistentClass) {
    super(persistentClass);
  }

  @Override
  public LogMapdisplayTo findFirst() {

    return null;
  }

}
