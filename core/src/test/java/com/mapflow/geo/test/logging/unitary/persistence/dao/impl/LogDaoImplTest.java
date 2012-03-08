package com.mapflow.geo.test.logging.unitary.persistence.dao.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapflow.geo.common.exceptions.MapflowAppException;
import com.mapflow.geo.logging.dao.LogDao;
import com.mapflow.geo.logging.model.LogCounterFeaturesTo;
import com.mapflow.geo.logging.model.LogMapdisplayTo;
import com.mapflow.geo.logging.model.entities.MfServiceLog;
import com.mapflow.geo.test.logging.unitary.factory.LoggingFactoryObjects;
import com.mapflow.test.dao.BaseDaoTestCase;

public class LogDaoImplTest extends BaseDaoTestCase {

  private static final String TRANSACTION_ID = "461110";

  @Autowired
  private LogDao logDao;

  @Test
  public void testRetrieveLogRowFromDatabase() {

    final List<MfServiceLog> mfServiceLog = logDao.getTransactionId(TRANSACTION_ID);

    assertThat(mfServiceLog.isEmpty(), is(false));
  }

  /**
   * - test that an empty iterator is returned if the collection of collections has no contents
   * 
   * @throws MapflowAppException
   */
  @Test
  public void testSaveLogCountFeatureRowInDatabase() throws MapflowAppException {

    LogCounterFeaturesTo log = LoggingFactoryObjects.buildLogCountFeaturesExample();

    System.out.println("Trying to save in database log bean");

    final MfServiceLog mfServiceLog = logDao.save(log.getMfServiceLog());

    log = new LogCounterFeaturesTo(mfServiceLog);

    System.out.println("Saved in database log bean: " + log.toString());

    assertNotNull(log);
    assertNotNull(log.getId());
  }

  /**
   * - test that an empty iterator is returned if the collection of collections has no contents
   * 
   * @throws MapflowAppException
   */
  public void testSaveLogMapdisplayRowInDatabase() throws MapflowAppException {

    LogMapdisplayTo log = LoggingFactoryObjects.buildLogMapdisplayExample();

    System.out.println("Trying to save in database log bean");

    log = new LogMapdisplayTo(logDao.save(log.getMfServiceLog()));

    System.out.println("Saved in database log bean: " + log.toString());

    assertNotNull(log);
    assertNotNull(log.getId());
  }

}
