package com.mapflow.geo.test.logging.unitary.mock;

import java.util.Date;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.Mockery;

import com.mapflow.geo.exceptions.MapflowAppException;
import com.mapflow.geo.logging.dao.LogCountPointsDao;
import com.mapflow.geo.logging.dao.LogMapdisplayDao;
import com.mapflow.geo.logging.model.LogCounterFeaturesTo;
import com.mapflow.geo.logging.model.LogMapdisplayTo;
import com.mapflow.geo.logging.types.FeaturesCategoryType;
import com.mapflow.geo.test.logging.unitary.mock.matchers.LogIdEqual;

public class FactoryMock {

  @Factory
  public static Matcher<LogMapdisplayTo> aUserNameEqual(final String name) {
    return new LogIdEqual(name);
  }

  private final Mockery context = new Mockery();
  private final LogMapdisplayDao logDao;
  private final LogCountPointsDao logCounterDao;

  public FactoryMock() {

    final String name = "user";

    logDao = context.mock(LogMapdisplayDao.class);
    logCounterDao = context.mock(LogCountPointsDao.class);

    try {
      configureLogDaoSave(buildLogExample(), name);
      configureLogCounterDaoSave(buildLogCounterExample(), name);
    }
    catch (final MapflowAppException e) {
      e.printStackTrace();
    }
  }

  private LogCounterFeaturesTo buildLogCounterExample() {

    final LogCounterFeaturesTo log = new LogCounterFeaturesTo();

    // final Transaction transaction = new Transaction();

    log.setId("11111");
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
    log.setFeaturesCategory(FeaturesCategoryType.Claims);

    return log;
  }

  private LogMapdisplayTo buildLogExample() throws MapflowAppException {

    final LogMapdisplayTo log = new LogMapdisplayTo();

    // final Transaction transaction = new Transaction();
    //
    log.setId("11111");
    log.setClientHostname(null);
    log.setClientIP("192.168.50.240");
    log.setCustomerName("RA_AXA");
    log.setDuration(406);
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

  private void configureLogCounterDaoSave(final LogCounterFeaturesTo buildLogExample,
    final String name) {

    context.checking(new Expectations() {

      {

        // BeanUtils.populate(userSaved, ResourceBundle.getBundle(FactoryMock.class.getName()));
        allowing(logCounterDao).save(with(any(LogCounterFeaturesTo.class)));
        will(returnValue(buildLogCounterExample()));

        ignoring(logCounterDao);
      }
    });
  }

  /**
   * 
   */
  private void configureLogDaoSave(final LogMapdisplayTo log, final String id) {

    context.checking(new Expectations() {

      {

        // BeanUtils.populate(userSaved, ResourceBundle.getBundle(FactoryMock.class.getName()));
        allowing(logDao).save(with(any(LogMapdisplayTo.class)));
        will(returnValue(log));

        ignoring(logDao);
      }
    });
  }

  public LogCountPointsDao getLogCounterDao() {

    return logCounterDao;
  }

  /**
   * @return the logDao
   */
  public final LogMapdisplayDao getLogMapdisplayDao() {
    return logDao;
  }

}
