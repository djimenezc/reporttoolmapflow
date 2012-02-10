package com.mapflow.geo.logging.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

import com.mapflow.geo.logging.model.entities.MfServiceLog;
import com.mapflow.geo.logging.types.FeaturesCategoryType;
import com.mapflow.model.BaseObject;

public class LogCounterFeaturesTo extends BaseObject {

  private static final long serialVersionUID = -6725055993229100539L;

  private MfServiceLog mfServiceLog;
  


  /**
   * @return the clientHostname
   */
  public String getClientHostname() {
    return mfServiceLog.getClientHostname();
  }

  /**
   * @return the clientIP
   */
  public String getClientIp() {
    return mfServiceLog.getClientIp();
  }

  /**
   * @return the customerName
   */
  public String getCustomerName() {
    
    return mfServiceLog.getUserField0().split(";")[0];
  }

  /**
   * @return the featuresTypes
   */
  public FeaturesCategoryType getFeaturesCategory() {
    return mfServiceLog.getFeaturesCategory();
  }

  public String getFeaturesType() {
    return mfServiceLog.getFeaturesType();
  }

  /**
   * @return the id
   */
  public String getId() {
    
    return Long.toString(mfServiceLog.getId());
  }

  /**
   * @return the layerCount
   */
  public String getLayerCount() {
    return layerCount;
  }

  /**
   * @return the mapStyle
   */
  public String getMapStyle() {
    return mapStyle;
  }

  /**
   * @return the requestDate
   */
  public Date getRequestDate() {
    return requestDate;
  }

  /**
   * @return the serviceHost
   */
  public String getServiceHost() {
    return serviceHost;
  }

  /**
   * @return the serviceName
   */
  public String getServiceName() {
    return serviceName;
  }

  /**
   * @return the xcoord
   */
  public String getXcoord() {
    return xcoord;
  }

  /**
   * @return the ycoord
   */
  public String getYcoord() {
    return ycoord;
  }

  /**
   * @return the zoomLevel
   */
  public String getZoomLevel() {
    return zoomLevel;
  }

  @Override
  public int hashCode() {
    
    return mfServiceLog.hashCode();
  }

  /**
   * @param clientHostname
   *          the clientHostname to set
   */
  public void setClientHostname(final String clientHostname) {
    this.clientHostname = clientHostname;
  }

  /**
   * @param clientIP
   *          the clientIP to set
   */
  public void setClientIP(final String clientIP) {
    this.clientIP = clientIP;
  }

  /**
   * @param customerName
   *          the customerName to set
   */
  public void setCustomerName(final String customerName) {
    this.customerName = customerName;
  }

  /**
   * @param featuresTypes
   *          the featuresTypes to set
   */
  public void setFeaturesCategory(final FeaturesCategoryType featuresTypes) {
    featuresCategory = featuresTypes;
  }

  public void setFeaturesType(final String featuresType) {
    this.featuresType = featuresType;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * @param layerCount
   *          the layerCount to set
   */
  public void setLayerCount(final String layerCount) {
    this.layerCount = layerCount;
  }

  /**
   * @param mapStyle
   *          the mapStyle to set
   */
  public void setMapStyle(final String mapStyle) {
    this.mapStyle = mapStyle;
  }

  /**
   * @param requestDate
   *          the requestDate to set
   */
  public void setRequestDate(final Date requestDate) {
    this.requestDate = requestDate;
  }

  /**
   * @param serviceHost
   *          the serviceHost to set
   */
  public void setServiceHost(final String serviceHost) {
    this.serviceHost = serviceHost;
  }

  /**
   * @param serviceName
   *          the serviceName to set
   */
  public void setServiceName(final String serviceName) {
    this.serviceName = serviceName;
  }

  /**
   * @param xcoord
   *          the xcoord to set
   */
  public void setXcoord(final String xcoord) {
    
    mfServiceLog.setUserField1(xcoord);
  }

  /**
   * @param ycoord
   *          the ycoord to set
   */
  public void setYcoord(final String ycoord) {
    
    mfServiceLog.setUserField2(ycoord);
  }

  /**
   * @param zoomLevel
   *          the zoomLevel to set
   */
  public void setZoomLevel(final String zoomLevel) {
    
    mfServiceLog.setUserField4(zoomLevel);
  }

  @Override
  public String toString() {
    
    return mfServiceLog.toString();
  }

}
