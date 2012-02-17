package com.mapflow.geo.logging.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapflow.geo.logging.dao.LogDao;
import com.mapflow.geo.logging.model.LogCounterFeaturesTo;
import com.mapflow.geo.logging.model.LogMapdisplayTo;
import com.mapflow.geo.logging.model.entities.MfServiceLog;
import com.mapflow.geo.logging.types.FeaturesCategoryType;

@Service("loggerService")
@WebService(serviceName = "loggerService", endpointInterface = "com.mapflow.geo.logging.service.LoggerDatabaseService")
public class LoggerDatabaseServiceImpl implements LoggerService {

  private final Logger logger = Logger.getLogger(getClass());

  @Autowired
  private LogDao logDao;

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

  public LogDao getLogDao() {
    return logDao;
  }

  @Override
  public Long registerFeatureDisplay(final String clientIP, final String customerName,
    final int duration, final String count, final String mapStyle, final String xcoord,
    final String ycoord, final String zoomLevel, final FeaturesCategoryType featureCategory,
    final String featureType) throws URISyntaxException {

    Long result = -1L;

    LogCounterFeaturesTo log = new LogCounterFeaturesTo();

    log.setClientHostname(null);
    log.setClientIp(clientIP);
    log.setCustomerName(customerName);
    log.setLayerCount(count);
    log.setMapStyle(mapStyle);
    log.setRequestDate(new Date());
    log.setServiceHost(null);
    log.setXcoord(xcoord);
    log.setYcoord(ycoord);
    log.setZoomLevel(zoomLevel);

    log.setFeaturesCategory(featureCategory);
    log.setFeaturesType(featureType);

    if (logDao != null) {
      MfServiceLog mfServiceLog = logDao.save(log.getMfServiceLog());

      log.setMfServiceLog(mfServiceLog);
    }

    if (log.getId() != null) {

      result = log.getId();
    }

    return result;
  }

  @Override
  public Long registerMapDisplayRequest(final String clientIP, final String customerName,
    final int duration, final String heigth, final String width, final String layer,
    final String count, final String mapStyle, final String mineType, final String serviceUrl,
    final String xcoord, final String ycoord, final String zoomLevel) {

    Long result = -1L;

    LogMapdisplayTo log = new LogMapdisplayTo();

    log.setClientHostname(null);
    log.setClientIp(clientIP);
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

    log.setTicket(null);
    log.setUserName(null);
    log.setXcoord(xcoord);
    log.setYcoord(ycoord);
    log.setZoomLevel(zoomLevel);

    if (logDao != null) {
      MfServiceLog mfServiceLog = logDao.save(log.getMfServiceLog());

      log.setMfServiceLog(mfServiceLog);
    }

    if (log.getId() != null) {

      result = log.getId();
    }

    return result;
  }

  public void setLogDao(final LogDao logDao) {

    this.logDao = logDao;
  }

}
