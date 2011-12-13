package com.mapflow.geo.test.logging.unitary.service;

import static org.junit.Assert.assertNotNull;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.mapflow.geo.exceptions.MapflowAppException;
import com.mapflow.geo.logging.services.LoggerDatabaseServiceImpl;
import com.mapflow.geo.logging.types.FeaturesCategoryType;
import com.mapflow.geo.test.logging.unitary.mock.FactoryMock;

public class LoggerDatabaseServiceImplTest {

  private LoggerDatabaseServiceImpl loggerService;

  private FactoryMock factoryMock;

  @Before
  public void setUp() throws Exception {

    loggerService = new LoggerDatabaseServiceImpl("", null);

    factoryMock = new FactoryMock();

    loggerService.setLogMapdisplayDao(factoryMock.getLogMapdisplayDao());
    loggerService.setLogCounterDao(factoryMock.getLogCounterDao());
  }

  /**
   * - test that an empty iterator is returned if the collection of collections has no contents
   * 
   * @throws MapflowAppException
   * @throws URISyntaxException
   */
  @Test
  public void testRegisterCounterFeature() throws MapflowAppException, URISyntaxException {

    final String clientIP = "192.168.50.240";
    final String customerName = "RA_AXA";
    final int duration = 406;
    final String ycoord = "-258149.52722502805";
    final String xcoord = "7058275.946559923";
    final String mapStyle = "ACETATEMC";
    final String count = "2";
    final String zoomLevel = "1";

    final String result =
      loggerService.registerFeatureDisplay(clientIP, customerName, duration, count, mapStyle,
        xcoord, ycoord, zoomLevel, FeaturesCategoryType.Comah, "hello");

    System.out.println("Register in database event with id: " + result);

    assertNotNull(result);
  }

  /**
   * - test that an empty iterator is returned if the collection of collections has no contents
   * 
   * @throws MapflowAppException
   * @throws URISyntaxException
   */
  // @Test(expected = java.lang.IllegalArgumentException.class)
  // public void testRegisterCounterFeatureIncorrectType() throws MapflowAppException,
  // URISyntaxException {
  //
  // final String clientIP = "192.168.50.240";
  // final String customerName = "RA_AXA";
  // final int duration = 406;
  // final String ycoord = "-258149.52722502805";
  // final String xcoord = "7058275.946559923";
  // final String mapStyle = "ACETATEMC";
  // final String count = "2";
  // final String zoomLevel = "1";
  // final String featureType = "Comahaa";
  //
  // final String result =
  // loggerService.registerFeatureDisplay(clientIP, customerName, duration, count,
  // mapStyle, xcoord, ycoord, zoomLevel, featureType);
  //
  // System.out.println("Register in database event with id: " + result);
  //
  // assertNotNull(result);
  // }

  /**
   * - test that an empty iterator is returned if the collection of collections has no contents
   * 
   * @throws MapflowAppException
   * @throws URISyntaxException
   */
  @Test
  public void testRegisterDisplayEvent() throws MapflowAppException, URISyntaxException {

    final String clientIP = "192.168.50.240";
    final String customerName = "RA_AXA";
    final int duration = 406;
    final String heigth = "521";
    final String ycoord = "-258149.52722502805";
    final String xcoord = "7058275.946559923";
    final String serviceHost =
      "http://mapsrv.mapflow.com/tilecache/0/{location}/{zoom}/{x}/{y}.png?type=google&transparent=true";
    final String mineType = "image/png";
    final String mapStyle = "ACETATEMC";
    final String count = "2";
    final String layer = "NAFRA";
    final String width = "";
    final String zoomLevel = "1";

    final String result =
      loggerService.registerMapDisplayRequest(clientIP, customerName, duration, heigth, width,
        layer, count, mapStyle, mineType, serviceHost, xcoord, ycoord, zoomLevel);

    System.out.println("Register in database event with id: " + result);

    assertNotNull(result);
  }
}
