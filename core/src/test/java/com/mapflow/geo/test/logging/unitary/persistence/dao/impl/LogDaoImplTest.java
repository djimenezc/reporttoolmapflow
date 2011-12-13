package com.mapflow.geo.test.logging.unitary.persistence.dao.impl;

import java.util.Date;

import junit.framework.TestCase;

import com.mapflow.geo.exceptions.MapflowAppException;
import com.mapflow.geo.helper.dataaccess.DBHelper;
import com.mapflow.geo.logging.dao.LogCountPointsDao;
import com.mapflow.geo.logging.dao.LogMapdisplayDao;
import com.mapflow.geo.logging.dao.impl.LogCountDaoImpl;
import com.mapflow.geo.logging.dao.impl.LogMapdisplayDaoImpl;
import com.mapflow.geo.logging.model.LogCounterFeaturesTo;
import com.mapflow.geo.logging.model.LogMapdisplayTo;
import com.mapflow.geo.logging.types.FeaturesCategoryType;

public class LogDaoImplTest extends TestCase {

  private static final String TRANSACTION_ID = "46180";

  private LogMapdisplayDao logMapdisplayDao;

  private LogCountPointsDao logCountPointsDao;

  private static final String SERVICE_NAME_EXPECTED = null;

  private static final String URL = "jdbc:oracle:thin:@192.168.160.108:1521/GEOSUAT.MAPFLOW.LOCAL";

  private static final String PASSWORD = "Geoueu1ns";

  private static final String USER = "GEOUEU";

  private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

  private LogCounterFeaturesTo buildLogCountFeaturesExample() throws MapflowAppException {

    final LogCounterFeaturesTo log = new LogCounterFeaturesTo();

    log.setClientHostname(null);
    log.setClientIP("192.168.50.240");
    log.setCustomerName("RA_AXA");
    log.setLayerCount("2");
    log.setMapStyle("ACETATEMCS");
    log.setRequestDate(new Date());
    log.setServiceHost("UATAPP1-UK");
    log.setServiceName("MAPDISPLAY");
    log.setXcoord("-258149.52722502805");
    log.setYcoord("7058275.946559923");
    log.setZoomLevel("1");
    log.setLayerCount("18");
    log.setFeaturesCategory(FeaturesCategoryType.Comah);

    return log;
  }

  private LogMapdisplayTo buildLogMapdisplayExample() throws MapflowAppException {

    final LogMapdisplayTo log = new LogMapdisplayTo();

    // final Transaction transaction = new Transaction();
    //
    // log.setId(transaction.getID());
    log.setClientHostname(null);
    log.setClientIP("192.168.50.240");
    log.setCustomerName("RA_AXA");
    log.setDuration(406);
    // log. setId(String);
    log.setImageHeight("521");
    log.setImageWidth("1263");
    log.setLayer("NAFRA");
    log.setLayerCount("2");
    log.setMapStyle("ACETATEMCS");
    log.setMimeType("image/png");
    log.setRequestDate(new Date());
    log.setServiceHost("UATAPP1-UK");
    log.setServiceName("MAPDISPLAY");
    log.setTicket(null);
    log.setUSER_FIELD_9(null);
    log.setXcoord("-258149.52722502805");
    log.setYcoord("7058275.946559923");
    log.setZoomLevel("1");

    return log;
  }

  @Override
  protected void setUp() throws Exception {

    final DBHelper dbhelper = new DBHelper();

    logMapdisplayDao =
      new LogMapdisplayDaoImpl(LogMapdisplayTo.class, DRIVER, USER, PASSWORD, URL, dbhelper);

    logCountPointsDao =
      new LogCountDaoImpl(LogCounterFeaturesTo.class, DRIVER, USER, PASSWORD, URL, dbhelper);

    super.setUp();
  }

  public void testRetrieveLogRowFromDatabase() {

    final LogMapdisplayTo log = logMapdisplayDao.get(TRANSACTION_ID);

    assertNull(SERVICE_NAME_EXPECTED, log.getServiceName());
  }

  /**
   * - test that an empty iterator is returned if the collection of collections has no contents
   * 
   * @throws MapflowAppException
   */
  public void testSaveLogCountFeatureRowInDatabase() throws MapflowAppException {

    LogCounterFeaturesTo log = buildLogCountFeaturesExample();

    System.out.println("Trying to save in database log bean");

    log = logCountPointsDao.save(log);

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

    LogMapdisplayTo log = buildLogMapdisplayExample();

    System.out.println("Trying to save in database log bean");

    log = logMapdisplayDao.save(log);

    System.out.println("Saved in database log bean: " + log.toString());

    assertNotNull(log);
    assertNotNull(log.getId());
  }

}
