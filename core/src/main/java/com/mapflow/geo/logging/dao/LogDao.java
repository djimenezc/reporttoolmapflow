package com.mapflow.geo.logging.dao;

import java.util.List;

import com.mapflow.geo.common.persistence.GenericDaoExtend;
import com.mapflow.geo.logging.model.entities.MfServiceLog;

/**
 * @author djimenez
 */
public interface LogDao extends GenericDaoExtend<MfServiceLog, Long> {

  List<MfServiceLog> getTransactionId(String transactionId);

}
