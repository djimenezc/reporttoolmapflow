package com.mapflow.geo.logging.services.locator;

import com.mapflow.geo.logging.services.LoggerService;

public interface ServiceLocator {

  public LoggerService loggerServiceFinder();

}
