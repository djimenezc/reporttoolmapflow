package com.mapflow.geo.logging.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mapflow.geo.helper.dataaccess.DBHelper;
import com.mapflow.geo.logging.dao.LogCountPointsDao;
import com.mapflow.geo.logging.dao.LogMapdisplayDao;
import com.mapflow.geo.logging.dao.impl.LogCountDaoImpl;
import com.mapflow.geo.logging.dao.impl.LogMapdisplayDaoImpl;
import com.mapflow.geo.logging.model.LogCounterFeaturesTo;
import com.mapflow.geo.logging.model.LogMapdisplayTo;
import com.mapflow.geo.logging.types.FeaturesCategoryType;

public class LoggerDatabaseServiceImpl implements LoggerService {

  private final Logger logger = Logger.getLogger(getClass());

  private LogMapdisplayDao logMapdisplayDao;
  private LogCountPointsDao logCounterDao;

  public LoggerDatabaseServiceImpl(final String jndiName, final DBHelper dbHelper) {

    logMapdisplayDao = new LogMapdisplayDaoImpl(LogMapdisplayTo.class, jndiName, dbHelper);
    logCounterDao = new LogCountDaoImpl(LogCounterFeaturesTo.class, jndiName, dbHelper);
  }

  private String extractDomain(final String serviceUrl) {

    // Extract the host of the url
    String serviceDomain = "";
    try {
      final URI uri = new URI(serviceUrl);
      serviceDomain = uri.getHost();
    }
    catch (final Exception e) {

      serviceDomain = serviceUrl.split("/")[2];
    }

    if (logger.isDebugEnabled()) {
      logger.debug("Extract domain " + serviceDomain + " from url: " + serviceUrl);
    }

    return serviceDomain;
  }

  public LogMapdisplayDao getLogMapdisplayDao() {
    return logMapdisplayDao;
  }

  @Override
  public String registerFeatureDisplay(final String clientIP, final String customerName,
    final int duration, final String count, final String mapStyle, final String xcoord,
    final String ycoord, final String zoomLevel, final FeaturesCategoryType featureCategory,
    final String featureType) throws URISyntaxException {

    String result = "-1";

    LogCounterFeaturesTo log = new LogCounterFeaturesTo();

    log.setClientHostname(null);
    log.setClientIP(clientIP);
    log.setCustomerName(customerName);
    log.setLayerCount(count);
    log.setMapStyle(mapStyle);
    log.setRequestDate(new Date());
    log.setServiceHost(null);
    log.setServiceName("FEATURECOUNT");
    log.setXcoord(xcoord);
    log.setYcoord(ycoord);
    log.setZoomLevel(zoomLevel);

    log.setFeaturesCategory(featureCategory);
    log.setFeaturesType(featureType);

    if (logCounterDao != null) {
      log = logCounterDao.save(log);
    }

    if (log.getId() != null) {

      result = log.getId();
    }

    return result;
  }

  @Override
  public String registerMapDisplayRequest(final String clientIP, final String customerName,
    final int duration, final String heigth, final String width, final String layer,
    final String count, final String mapStyle, final String mineType, final String serviceUrl,
    final String xcoord, final String ycoord, final String zoomLevel) {

    String result = "-1";

    LogMapdisplayTo log = new LogMapdisplayTo();

    log.setClientHostname(null);
    log.setClientIP(clientIP);
    log.setCustomerName(customerName);
    log.setDuration(duration);
    log.setImageHeight(heigth);
    log.setImageWidth(width);
    log.setLayer(layer);
    log.setLayerCount(count);
    log.setMapStyle(mapStyle);
    log.setMimeType(mineType);
    log.setRequestDate(new Date());

    final String serviceDomain = extractDomain(serviceUrl);
    log.setServiceHost(serviceDomain);

    log.setServiceName("MAPDISPLAY");
    log.setTicket(null);
    log.setUSER_FIELD_9(null);
    log.setXcoord(xcoord);
    log.setYcoord(ycoord);
    log.setZoomLevel(zoomLevel);

    if (logMapdisplayDao != null) {
      log = logMapdisplayDao.save(log);
    }

    if (log.getId() != null) {

      result = log.getId();
    }

    return result;
  }

  public void setLogCounterDao(final LogCountPointsDao logCounterDao) {

    this.logCounterDao = logCounterDao;
  }

  public void setLogMapdisplayDao(final LogMapdisplayDao logDao) {
    logMapdisplayDao = logDao;
  }

}