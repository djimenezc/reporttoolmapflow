package com.mapflow.geo.test.logging.unitary.persistence.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapflow.geo.common.exceptions.MapflowAppException;
import com.mapflow.geo.logging.dao.LogDao;
import com.mapflow.geo.logging.model.LogCounterFeaturesTo;
import com.mapflow.geo.logging.model.LogMapdisplayTo;
import com.mapflow.geo.test.logging.unitary.factory.LoggingFactoryObjects;
import com.mapflow.test.dao.BaseDaoTestCase;

public class LogDaoImplTest extends BaseDaoTestCase {

  private static final String TRANSACTION_ID = "46180";

  @Autowired
  private LogDao logDao;

  private static final String SERVICE_NAME_EXPECTED = null;

  @Test
  public void testRetrieveLogRowFromDatabase() {

    final LogMapdisplayTo log = new LogMapdisplayTo(logDao.get(TRANSACTION_ID));

    assertNull(SERVICE_NAME_EXPECTED, log.getServiceName());
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

    log = new LogCounterFeaturesTo(logDao.save(log.getMfServiceLog()));

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
