package com.mapflow.geo.test.logging.unitary.factory;

import java.util.Date;

import com.mapflow.geo.common.exceptions.MapflowAppException;
import com.mapflow.geo.logging.model.LogCounterFeaturesTo;
import com.mapflow.geo.logging.model.LogMapdisplayTo;
import com.mapflow.geo.logging.types.FeaturesCategoryType;


public class LoggingFactoryObjects {

  public static LogMapdisplayTo buildLogMapdisplayExample() throws MapflowAppException {

    final LogMapdisplayTo log = new LogMapdisplayTo();

    // final Transaction transaction = new Transaction();
    //
    // log.setId(transaction.getID());
    log.setClientHostname(null);
    log.setClientIp("192.168.50.240");
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
    log.setTicket(null);
    log.setUserName(null);
    log.setXcoord("-258149.52722502805");
    log.setYcoord("7058275.946559923");
    log.setZoomLevel("1");

    return log;
  }
  
  public static LogCounterFeaturesTo buildLogCountFeaturesExample() throws MapflowAppException {

    final LogCounterFeaturesTo log = new LogCounterFeaturesTo();

    log.setClientHostname(null);
    log.setClientIp("192.168.50.240");
    log.setCustomerName("RA_AXA");
    log.setLayerCount("2");
    log.setMapStyle("ACETATEMCS");
    log.setRequestDate(new Date());
    log.setServiceHost("UATAPP1-UK");
    log.setXcoord("-258149.52722502805");
    log.setYcoord("7058275.946559923");
    log.setZoomLevel("1");
    log.setLayerCount("18");
    log.setFeaturesCategory(FeaturesCategoryType.Comah);

    return log;
  }
  
}
