package com.mapflow.geo.logging.model;

import java.util.Date;

import com.mapflow.geo.model.BaseObject;

public class LogMapdisplayTo extends BaseObject {

  private static final long serialVersionUID = -6725055993229100539L;

  private String id;
  private String serviceHost;
  private String serviceName;
  private String clientIP;
  private String clientHostname;
  private Date requestDate;
  private int duration;
  private String customerName;
  private String layer;
  private String xcoord;
  private String ycoord;
  private String imageHeight;
  private String imageWidth;
  private String mimeType;
  private String zoomLevel;
  private String mapStyle;
  private String layerCount;
  private String USER_FIELD_9;
  private String ticket;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final LogMapdisplayTo other = (LogMapdisplayTo) obj;
    if (USER_FIELD_9 == null) {
      if (other.USER_FIELD_9 != null) {
        return false;
      }
    }
    else if (!USER_FIELD_9.equals(other.USER_FIELD_9)) {
      return false;
    }
    if (clientHostname == null) {
      if (other.clientHostname != null) {
        return false;
      }
    }
    else if (!clientHostname.equals(other.clientHostname)) {
      return false;
    }
    if (clientIP == null) {
      if (other.clientIP != null) {
        return false;
      }
    }
    else if (!clientIP.equals(other.clientIP)) {
      return false;
    }
    if (customerName == null) {
      if (other.customerName != null) {
        return false;
      }
    }
    else if (!customerName.equals(other.customerName)) {
      return false;
    }
    if (duration != other.duration) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    }
    else if (!id.equals(other.id)) {
      return false;
    }
    if (imageHeight == null) {
      if (other.imageHeight != null) {
        return false;
      }
    }
    else if (!imageHeight.equals(other.imageHeight)) {
      return false;
    }
    if (imageWidth == null) {
      if (other.imageWidth != null) {
        return false;
      }
    }
    else if (!imageWidth.equals(other.imageWidth)) {
      return false;
    }
    if (layer == null) {
      if (other.layer != null) {
        return false;
      }
    }
    else if (!layer.equals(other.layer)) {
      return false;
    }
    if (layerCount == null) {
      if (other.layerCount != null) {
        return false;
      }
    }
    else if (!layerCount.equals(other.layerCount)) {
      return false;
    }
    if (mapStyle == null) {
      if (other.mapStyle != null) {
        return false;
      }
    }
    else if (!mapStyle.equals(other.mapStyle)) {
      return false;
    }
    if (mimeType == null) {
      if (other.mimeType != null) {
        return false;
      }
    }
    else if (!mimeType.equals(other.mimeType)) {
      return false;
    }
    if (requestDate == null) {
      if (other.requestDate != null) {
        return false;
      }
    }
    else if (!requestDate.equals(other.requestDate)) {
      return false;
    }
    if (serviceHost == null) {
      if (other.serviceHost != null) {
        return false;
      }
    }
    else if (!serviceHost.equals(other.serviceHost)) {
      return false;
    }
    if (serviceName == null) {
      if (other.serviceName != null) {
        return false;
      }
    }
    else if (!serviceName.equals(other.serviceName)) {
      return false;
    }
    if (ticket == null) {
      if (other.ticket != null) {
        return false;
      }
    }
    else if (!ticket.equals(other.ticket)) {
      return false;
    }
    if (xcoord == null) {
      if (other.xcoord != null) {
        return false;
      }
    }
    else if (!xcoord.equals(other.xcoord)) {
      return false;
    }
    if (ycoord == null) {
      if (other.ycoord != null) {
        return false;
      }
    }
    else if (!ycoord.equals(other.ycoord)) {
      return false;
    }
    if (zoomLevel == null) {
      if (other.zoomLevel != null) {
        return false;
      }
    }
    else if (!zoomLevel.equals(other.zoomLevel)) {
      return false;
    }
    return true;
  }

  public String getClientHostname() {
    return clientHostname;
  }

  public String getClientIP() {
    return clientIP;
  }

  public String getCustomerName() {
    return customerName;
  }

  public int getDuration() {
    return duration;
  }

  public String getId() {
    return id;
  }

  public String getImageHeight() {
    return imageHeight;
  }

  public String getImageWidth() {
    return imageWidth;
  }

  public String getLayer() {
    return layer;
  }

  public String getLayerCount() {
    return layerCount;
  }

  public String getMapStyle() {
    return mapStyle;
  }

  public String getMimeType() {
    return mimeType;
  }

  public Date getRequestDate() {
    return requestDate;
  }

  public String getServiceHost() {
    return serviceHost;
  }

  public String getServiceName() {
    return serviceName;
  }

  public String getTicket() {
    return ticket;
  }

  public String getUSER_FIELD_9() {
    return USER_FIELD_9;
  }

  public String getXcoord() {
    return xcoord;
  }

  public String getYcoord() {
    return ycoord;
  }

  public String getZoomLevel() {
    return zoomLevel;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((USER_FIELD_9 == null) ? 0 : USER_FIELD_9.hashCode());
    result = (prime * result) + ((clientHostname == null) ? 0 : clientHostname.hashCode());
    result = (prime * result) + ((clientIP == null) ? 0 : clientIP.hashCode());
    result = (prime * result) + ((customerName == null) ? 0 : customerName.hashCode());
    result = (prime * result) + duration;
    result = (prime * result) + ((id == null) ? 0 : id.hashCode());
    result = (prime * result) + ((imageHeight == null) ? 0 : imageHeight.hashCode());
    result = (prime * result) + ((imageWidth == null) ? 0 : imageWidth.hashCode());
    result = (prime * result) + ((layer == null) ? 0 : layer.hashCode());
    result = (prime * result) + ((layerCount == null) ? 0 : layerCount.hashCode());
    result = (prime * result) + ((mapStyle == null) ? 0 : mapStyle.hashCode());
    result = (prime * result) + ((mimeType == null) ? 0 : mimeType.hashCode());
    result = (prime * result) + ((requestDate == null) ? 0 : requestDate.hashCode());
    result = (prime * result) + ((serviceHost == null) ? 0 : serviceHost.hashCode());
    result = (prime * result) + ((serviceName == null) ? 0 : serviceName.hashCode());
    result = (prime * result) + ((ticket == null) ? 0 : ticket.hashCode());
    result = (prime * result) + ((xcoord == null) ? 0 : xcoord.hashCode());
    result = (prime * result) + ((ycoord == null) ? 0 : ycoord.hashCode());
    result = (prime * result) + ((zoomLevel == null) ? 0 : zoomLevel.hashCode());
    return result;
  }

  public void setClientHostname(final String clientHostname) {
    this.clientHostname = clientHostname;
  }

  public void setClientIP(final String clientIP) {
    this.clientIP = clientIP;
  }

  public void setCustomerName(final String customerName) {
    this.customerName = customerName;
  }

  public void setDuration(final int duration) {
    this.duration = duration;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public void setImageHeight(final String imageHeight) {
    this.imageHeight = imageHeight;
  }

  public void setImageWidth(final String imageWidth) {
    this.imageWidth = imageWidth;
  }

  public void setLayer(final String layer) {
    this.layer = layer;
  }

  public void setLayerCount(final String layerCount) {
    this.layerCount = layerCount;
  }

  public void setMapStyle(final String mapStyle) {
    this.mapStyle = mapStyle;
  }

  public void setMimeType(final String mimeType) {
    this.mimeType = mimeType;
  }

  public void setRequestDate(final Date requestData) {
    requestDate = requestData;
  }

  public void setServiceHost(final String serviceHost) {
    this.serviceHost = serviceHost;
  }

  public void setServiceName(final String serviceName) {
    this.serviceName = serviceName;
  }

  public void setTicket(final String ticket) {
    this.ticket = ticket;
  }

  public void setUSER_FIELD_9(final String uSER_FIELD_9) {
    USER_FIELD_9 = uSER_FIELD_9;
  }

  public void setXcoord(final String xcoord) {
    this.xcoord = xcoord;
  }

  public void setYcoord(final String ycoord) {
    this.ycoord = ycoord;
  }

  public void setZoomLevel(final String zoomLevel) {
    this.zoomLevel = zoomLevel;
  }

  @Override
  public String toString() {
    return "LoggingTo [id=" + id + ", serviceHost=" + serviceHost + ", serviceName=" + serviceName
      + ", clientIP=" + clientIP + ", clientHostname=" + clientHostname + ", requestData="
      + requestDate + ", duration=" + duration + ", customerName=" + customerName + ", layer="
      + layer + ", xcoord=" + xcoord + ", ycoord=" + ycoord + ", imageHeight=" + imageHeight
      + ", imageWidth=" + imageWidth + ", mimeType=" + mimeType + ", zoomLevel=" + zoomLevel
      + ", mapStyle=" + mapStyle + ", layerCount=" + layerCount + ", USER_FIELD_9=" + USER_FIELD_9
      + ", ticket=" + ticket + "]";
  }

}
