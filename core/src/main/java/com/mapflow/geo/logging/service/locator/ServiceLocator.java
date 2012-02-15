package com.mapflow.geo.logging.service.locator;

import com.mapflow.geo.logging.service.LoggerService;

public interface ServiceLocator {

  public LoggerService loggerServiceFinder();

}
