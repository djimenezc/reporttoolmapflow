package com.mapflow.geo.logging.services;

import java.net.URISyntaxException;

import com.mapflow.geo.logging.types.FeaturesCategoryType;

/**
 * Service to register in data source Underwriting events
 * 
 * @author david
 */
public interface LoggerService {

  /**
   * Method to register in data source Underwriting events, with this information is possible to
   * calculate the cost of a request. In this case register the points were showed.
   * 
   * @param clientIP
   *          Ip address of client
   * @param customerName
   *          Customer id
   * @param duration
   *          Request duration in millisecs
   * @param layer
   *          Layer id
   * @param count
   *          Layer count (includes Google map)
   * @param mapStyle
   *          Style of map
   * @param serviceUrl
   *          Server name running mapflow, pass the url
   * @param serviceName
   *          Name of the service
   * @param xcoord
   *          Coordinates of map centrepoint, horizontal
   * @param ycoord
   *          Coordinates of map centrepoint, vertical
   * @param zoomLevel
   *          Zoom level
   * @param featureCategory
   * @param featureType
   * @return Return the id of the event registered.
   * @throws URISyntaxException
   */
  String registerFeatureDisplay(String clientIP, String customerName, int duration, String count,
    String mapStyle, String xcoord, String ycoord, String zoomLevel,
    FeaturesCategoryType featureCategory, String featureType) throws URISyntaxException;

  /**
   * Method to register in data source Underwriting events, with this information is possible to
   * calculate the cost of a request
   * 
   * @param clientIP
   *          Ip address of client
   * @param customerName
   *          Customer id
   * @param duration
   *          Request duration in millisecs
   * @param heigth
   *          Image height showed
   * @param width
   *          Image width showed
   * @param layer
   *          Layer id
   * @param count
   *          Layer count (includes Google map)
   * @param mapStyle
   *          Style of map
   * @param mineType
   *          Image MimeType
   * @param serviceUrl
   *          Server name running mapflow, pass the url
   * @param xcoord
   *          Coordinates of map centrepoint, horizontal
   * @param ycoord
   *          Coordinates of map centrepoint, vertical
   * @param zoomLevel
   *          Zoom level
   * @return Return the id of the event registered.
   * @throws URISyntaxException
   *           Throw when the serviceHost
   */
  String registerMapDisplayRequest(String clientIP, String customerName, int duration,
    String heigth, String width, String layer, String count, String mapStyle, String mineType,
    String serviceUrl, String xcoord, String ycoord, String zoomLevel) throws URISyntaxException;

}
