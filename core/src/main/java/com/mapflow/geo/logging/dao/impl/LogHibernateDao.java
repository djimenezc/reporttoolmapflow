package com.mapflow.geo.logging.dao.impl;

import java.util.List;

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
public class LogHibernateDao extends GenericDaoHibernate<MfServiceLog, Long> implements LogDao {

  public LogHibernateDao() {
    super(MfServiceLog.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<MfServiceLog> getTransactionId(String transactionId) {

    final String queryString = "from MfServiceLog where TRANSACTION_ID=?";

    List<?> result = getHibernateTemplate().find(queryString, transactionId);

    return (List<MfServiceLog>) result;
  }

  @Override
  public MfServiceLog findFirst() {
    // TODO Auto-generated method stub
    return null;
  }

}
