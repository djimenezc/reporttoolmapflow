package com.mapflow.geo.logging.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mapflow.geo.logging.model.entities.MfServiceLog;
import com.mapflow.model.BaseObject;

public class LogMapdisplayTo extends BaseObject {

  private static final long serialVersionUID = -6725055993229100539L;

  private static final String SERVICE_NAME = "MAPDISPLAY";

  private MfServiceLog mfServiceLog;

  public LogMapdisplayTo() {

    mfServiceLog = new MfServiceLog();
    mfServiceLog.setServiceHost(SERVICE_NAME);
  }

  public LogMapdisplayTo(MfServiceLog mfServiceLog2) {

    this.setMfServiceLog(mfServiceLog2);
  }

  public MfServiceLog getMfServiceLog() {
    return mfServiceLog;
  }

  public void setMfServiceLog(MfServiceLog mfServiceLog) {
    this.mfServiceLog = mfServiceLog;
  }

  public String getClientHostname() {

    return mfServiceLog.getClientHostname();
  }

  public String getClientIp() {

    return mfServiceLog.getClientIp();
  }

  public String getCustomerName() {

    String customerName = mfServiceLog.getUserField0().split(MfServiceLog.USER_FIELD_SEPARATOR)[0];

    if (customerName == null) {
      customerName = "";
    }

    return customerName;
  }

  public int getDuration() {

    return mfServiceLog.getDuration().intValue();
  }

  public Long getId() {
    return mfServiceLog.getId();
  }
  
  public String getTransactionId()
  {
    return mfServiceLog.getTransactionId();
  }

  public String getImageHeight() {

    return mfServiceLog.getUserField3();
  }

  public String getImageWidth() {
    return mfServiceLog.getUserField4();
  }

  public String getLayer() {

    String layer = mfServiceLog.getUserField0().split(MfServiceLog.USER_FIELD_SEPARATOR)[1];

    if (layer == null) {
      layer = "";
    }

    return layer;
  }

  public String getLayerCount() {
    return mfServiceLog.getUserField8();
  }

  public String getMapStyle() {
    return mfServiceLog.getUserField7();
  }

  public String getMimeType() {
    return mfServiceLog.getUserField5();
  }

  public Date getRequestDate() {
    return mfServiceLog.getRequestDate();
  }

  public String getServiceHost() {
    return mfServiceLog.getServiceHost();
  }

  public String getTicket() {
    return mfServiceLog.getTicket();
  }

  public String getUserName() {
    return mfServiceLog.getUserField9();
  }

  public String getXcoord() {
    return mfServiceLog.getUserField1();
  }

  public String getYcoord() {
    return mfServiceLog.getUserField2();
  }

  public String getZoomLevel() {
    return mfServiceLog.getUserField6();
  }

  public void setClientHostname(final String clientHostname) {
    mfServiceLog.setClientHostname(clientHostname);
  }

  public void setClientIp(final String clientIP) {
    mfServiceLog.setClientIp(clientIP);
  }

  public void setCustomerName(final String customerName) {

    if (mfServiceLog.getUserField0() == null || mfServiceLog.getUserField0().equals("")) {
      mfServiceLog.setUserField0(customerName + MfServiceLog.USER_FIELD_SEPARATOR);
    }
    else {

      String userField0 = buildUserField0(customerName, getLayer());

      mfServiceLog.setUserField0(userField0);
    }
  }

  private String buildUserField0(final String customerName, String layer) {

    String userField0 = customerName + MfServiceLog.USER_FIELD_SEPARATOR + layer;

    return userField0;
  }

  public void setDuration(final int duration) {

    mfServiceLog.setDuration(new BigDecimal(duration));
  }

  public void setId(final Long id) {

    mfServiceLog.setId(id);
  }

  public void setImageHeight(final String imageHeight) {

    mfServiceLog.setUserField3(imageHeight);
  }

  public void setImageWidth(final String imageWidth) {
    mfServiceLog.setUserField4(imageWidth);
  }

  public void setLayer(final String layer) {

    if (mfServiceLog.getUserField0() == null || mfServiceLog.getUserField0().equals("")) {
      mfServiceLog.setUserField0(MfServiceLog.USER_FIELD_SEPARATOR + layer);
    }
    else {

      String userField0 = buildUserField0(getUserName(), layer);

      mfServiceLog.setUserField0(userField0);
    }
  }

  public void setLayerCount(final String layerCount) {

    mfServiceLog.setUserField8(layerCount);
  }

  public void setMapStyle(final String mapStyle) {
    mfServiceLog.setUserField7(mapStyle);
  }

  public void setMimeType(final String mimeType) {
    mfServiceLog.setUserField5(mimeType);
  }

  public void setRequestDate(final Date requestData) {
    mfServiceLog.setRequestDate(requestData);
  }

  public void setServiceHost(final String serviceHost) {
    mfServiceLog.setServiceHost(serviceHost);
  }

  public void setTicket(final String ticket) {
    mfServiceLog.setTicket(ticket);
  }

  public void setUserName(final String userName) {
    mfServiceLog.setUserField9(userName);
  }

  public void setXcoord(final String xcoord) {
    mfServiceLog.setUserField1(xcoord);
  }

  public void setYcoord(final String ycoord) {
    mfServiceLog.setUserField2(ycoord);
  }

  public void setZoomLevel(final String zoomLevel) {
    mfServiceLog.setUserField6(zoomLevel);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    LogMapdisplayTo other = (LogMapdisplayTo) obj;
    if (mfServiceLog == null) {
      if (other.mfServiceLog != null) return false;
    }
    else if (!mfServiceLog.equals(other.mfServiceLog)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mfServiceLog == null) ? 0 : mfServiceLog.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "LogMapdisplayTo [mfServiceLog=" + mfServiceLog + "]";
  }

  public String getServiceName() {
    return mfServiceLog.getServiceName();
  }

}
