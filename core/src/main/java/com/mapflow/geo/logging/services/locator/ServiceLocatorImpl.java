package com.mapflow.geo.logging.services.locator;

import com.mapflow.geo.helper.dataaccess.DBHelper;
import com.mapflow.geo.logging.services.LoggerDatabaseServiceImpl;
import com.mapflow.geo.logging.services.LoggerService;

public class ServiceLocatorImpl implements ServiceLocator {

  private static final String DATABASE_GEOUW_LOG = "DBDATASOURCE-LOG";

  private final LoggerService loggerService;

  private static ServiceLocatorImpl instance;

  public static ServiceLocatorImpl getInstance() {

    if (instance == null) {
      instance = new ServiceLocatorImpl(new DBHelper());
    }

    return instance;
  }

  private ServiceLocatorImpl(final DBHelper dbHelper) {

    loggerService = new LoggerDatabaseServiceImpl(DATABASE_GEOUW_LOG, dbHelper);
  }

  @Override
  public LoggerService loggerServiceFinder() {

    return loggerService;
  }

}
