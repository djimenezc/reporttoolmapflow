package com.mapflow.geo.logging.dao.impl;

import org.springframework.stereotype.Repository;

import com.mapflow.geo.common.persistence.hibernate.GenericDaoHibernate;
import com.mapflow.geo.logging.dao.LogDao;
import com.mapflow.geo.logging.model.entities.MfServiceLog;

/**
 * DAO to manage the CRUD operation with Log entries
 * 
 * @author djimenez
 */
@Repository("logMapdisplayDao")
public class LogHibernateDao extends GenericDaoHibernate<MfServiceLog, String>
  implements LogDao {

  public LogHibernateDao() {
    super(MfServiceLog.class);
  }

  @Override
  public MfServiceLog findFirst() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void remove(MfServiceLog entity) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public MfServiceLog save(MfServiceLog object) {
    // TODO Auto-generated method stub
    return null;
  }


}
