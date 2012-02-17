package com.mapflow.geo.logging.model;

import java.util.Date;

import com.mapflow.geo.logging.model.entities.MfServiceLog;
import com.mapflow.geo.logging.types.FeaturesCategoryType;
import com.mapflow.model.BaseObject;

public class LogCounterFeaturesTo extends BaseObject {

  private static final String SERVICE_NAME = "FEATURECOUNT";

  private static final long serialVersionUID = -6725055993229100539L;

  private MfServiceLog mfServiceLog;

  public LogCounterFeaturesTo() {

    mfServiceLog = new MfServiceLog();
    mfServiceLog.setServiceHost(SERVICE_NAME);
  }

  public LogCounterFeaturesTo(MfServiceLog mfServiceLog) {

    this.setMfServiceLog(mfServiceLog);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    LogCounterFeaturesTo other = (LogCounterFeaturesTo) obj;
    if (getMfServiceLog() == null) {
      if (other.getMfServiceLog() != null) return false;
    }
    else if (!getMfServiceLog().equals(other.getMfServiceLog())) return false;
    return true;
  }

  /**
   * @return the clientHostname
   */
  public String getClientHostname() {
    return getMfServiceLog().getClientHostname();
  }

  /**
   * @return the clientIP
   */
  public String getClientIp() {
    return getMfServiceLog().getClientIp();
  }

  /**
   * @return the customerName
   */
  public String getCustomerName() {

    return getMfServiceLog().getUserField0().split(MfServiceLog.USER_FIELD_SEPARATOR)[0];
  }

  /**
   * @return the featuresTypes
   */
  public FeaturesCategoryType getFeaturesCategory() {

    FeaturesCategoryType featureCategory =
      FeaturesCategoryType.valueOf(getMfServiceLog().getUserField3());

    return featureCategory;
  }

  /**
   * @return the id
   */
  public Long getId() {

    return getMfServiceLog().getId();
  }

  /**
   * @return the layerCount
   */
  public String getLayerCount() {
    return getMfServiceLog().getUserField6();
  }

  /**
   * @return the mapStyle
   */
  public String getMapStyle() {
    return getMfServiceLog().getUserField7();
  }

  public String getTransactionId() {
    return mfServiceLog.getTransactionId();
  }

  /**
   * @return the mapStyle
   */
  public String getUserName() {
    return getMfServiceLog().getUserField9();
  }

  /**
   * @return the requestDate
   */
  public Date getRequestDate() {
    return getMfServiceLog().getRequestDate();
  }

  /**
   * @return the serviceHost
   */
  public String getServiceHost() {

    return getMfServiceLog().getServiceHost();
  }

  /**
   * @return the xcoord
   */
  public String getXcoord() {
    return getMfServiceLog().getUserField1();
  }

  /**
   * @return the ycoord
   */
  public String getYcoord() {
    return getMfServiceLog().getUserField2();
  }

  /**
   * @return the zoomLevel
   */
  public String getZoomLevel() {
    return getMfServiceLog().getUserField5();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getMfServiceLog() == null) ? 0 : getMfServiceLog().hashCode());
    return result;
  }

  /**
   * @param clientHostname
   *          the clientHostname to set
   */
  public void setClientHostname(final String clientHostname) {

    getMfServiceLog().setClientHostname(clientHostname);
  }

  /**
   * @param clientIP
   *          the clientIP to set
   */
  public void setClientIp(final String clientIP) {

    getMfServiceLog().setClientIp(clientIP);
  }

  /**
   * @param customerName
   *          the customerName to set
   */
  public void setCustomerName(final String customerName) {

    getMfServiceLog().setUserField0(customerName);
  }

  /**
   * @param featuresTypes
   *          the featuresTypes to set
   */
  public void setFeaturesCategory(final FeaturesCategoryType featuresTypes) {

    getMfServiceLog().setUserField3(featuresTypes.getValue());
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(final Long id) {

    getMfServiceLog().setId(id);
  }

  /**
   * @param layerCount
   *          the layerCount to set
   */
  public void setLayerCount(final String layerCount) {

    getMfServiceLog().setUserField6(layerCount);
  }

  /**
   * @param userName
   *          the layerCount to set
   */
  public void setUserName(final String userName) {

    getMfServiceLog().setUserField9(userName);
  }

  /**
   * @param mapStyle
   *          the mapStyle to set
   */
  public void setMapStyle(final String mapStyle) {

    getMfServiceLog().setUserField7(mapStyle);
  }

  /**
   * @param requestDate
   *          the requestDate to set
   */
  public void setRequestDate(final Date requestDate) {

    getMfServiceLog().setRequestDate(requestDate);
  }

  /**
   * @param serviceHost
   *          the serviceHost to set
   */
  public void setServiceHost(final String serviceHost) {

    getMfServiceLog().setServiceHost(serviceHost);
  }

  /**
   * @param xcoord
   *          the xcoord to set
   */
  public void setXcoord(final String xcoord) {

    getMfServiceLog().setUserField1(xcoord);
  }

  /**
   * @param ycoord
   *          the ycoord to set
   */
  public void setYcoord(final String ycoord) {

    getMfServiceLog().setUserField2(ycoord);
  }

  /**
   * @param zoomLevel
   *          the zoomLevel to set
   */
  public void setZoomLevel(final String zoomLevel) {

    getMfServiceLog().setUserField5(zoomLevel);
  }

  @Override
  public String toString() {
    return "LogCounterFeaturesTo [mfServiceLog=" + getMfServiceLog() + "]";
  }

  public String getFeaturesType() {
    return getMfServiceLog().getUserField4();
  }

  public void setFeaturesType(String featureType) {

    getMfServiceLog().setUserField4(featureType);
  }

  public MfServiceLog getMfServiceLog() {
    return mfServiceLog;
  }

  public void setMfServiceLog(MfServiceLog mfServiceLog) {
    this.mfServiceLog = mfServiceLog;
  }

  public String getServiceName() {
    return mfServiceLog.getServiceName();
  }
}
