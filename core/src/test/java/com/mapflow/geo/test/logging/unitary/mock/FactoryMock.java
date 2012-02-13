package com.mapflow.geo.test.logging.unitary.mock;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.Mockery;

import com.mapflow.geo.common.exceptions.MapflowAppException;
import com.mapflow.geo.logging.dao.LogDao;
import com.mapflow.geo.logging.model.LogCounterFeaturesTo;
import com.mapflow.geo.logging.model.LogMapdisplayTo;
import com.mapflow.geo.logging.model.entities.MfServiceLog;
import com.mapflow.geo.test.logging.unitary.factory.LoggingFactoryObjects;
import com.mapflow.geo.test.logging.unitary.mock.matchers.LogIdEqual;

public class FactoryMock {

  @Factory
  public static Matcher<LogMapdisplayTo> aUserNameEqual(final String name) {
    return new LogIdEqual(name);
  }

  private final Mockery context = new Mockery();
  private final LogDao logDao;

  public FactoryMock() {

    final String name = "user";

    logDao = context.mock(LogDao.class);

    try {
      configureLogDaoSave(LoggingFactoryObjects.buildLogMapdisplayExample(), name);
      configureLogCounterDaoSave(LoggingFactoryObjects.buildLogCountFeaturesExample(), name);
    }
    catch (final MapflowAppException e) {
      e.printStackTrace();
    }
  }

  private void configureLogCounterDaoSave(final LogCounterFeaturesTo buildLogExample,
    final String name) {

    try {
      context.checking(new Expectations() {

        {

          // BeanUtils.populate(userSaved, ResourceBundle.getBundle(FactoryMock.class.getName()));
          allowing(logDao).save(with(any(MfServiceLog.class)));
          will(returnValue(LoggingFactoryObjects.buildLogCountFeaturesExample()));

          ignoring(logDao);
        }
      });
    }
    catch (MapflowAppException e) {
      e.printStackTrace();
    }
  }

  /**
   * 
   */
  private void configureLogDaoSave(final LogMapdisplayTo log, final String id) {

    context.checking(new Expectations() {

      {

        // BeanUtils.populate(userSaved, ResourceBundle.getBundle(FactoryMock.class.getName()));
        allowing(logDao).save(with(any(MfServiceLog.class)));
        will(returnValue(log.getMfServiceLog()));

        ignoring(logDao);
      }
    });
  }

  /**
   * @return the logDao
   */
  public final LogDao getLogMapdisplayDao() {
    return logDao;
  }

}
