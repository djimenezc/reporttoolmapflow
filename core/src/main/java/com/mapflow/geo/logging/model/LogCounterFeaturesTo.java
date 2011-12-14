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

import com.mapflow.geo.common.model.BaseObject;
import com.mapflow.geo.logging.types.FeaturesCategoryType;

@Entity
@Table(name = "MF_SERVICE_LOG", uniqueConstraints = @UniqueConstraint(columnNames = "MF_SERVICE_LOG"))
@Searchable
@XmlRootElement
public class LogCounterFeaturesTo extends BaseObject {

  private static final long serialVersionUID = -6725055993229100539L;

  private String id;

  private String serviceHost;

  private String serviceName;

  private String clientIP;

  private String clientHostname;

  private Date requestDate;

  private String customerName;

  private String xcoord;

  private String ycoord;

  private FeaturesCategoryType featuresCategory;

  private String featuresType;

  private String zoomLevel;

  private String mapStyle;

  private String layerCount;

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
    final LogCounterFeaturesTo other = (LogCounterFeaturesTo) obj;
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
    if (featuresCategory != other.featuresCategory) {
      return false;
    }
    if (featuresType == null) {
      if (other.featuresType != null) {
        return false;
      }
    }
    else if (!featuresType.equals(other.featuresType)) {
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

  /**
   * @return the clientHostname
   */
  @Column(name = "BRANCH_NAME", length = 20)
  public String getClientHostname() {
    return clientHostname;
  }

  /**
   * @return the clientIP
   */
  public String getClientIP() {
    return clientIP;
  }

  /**
   * @return the customerName
   */
  public String getCustomerName() {
    return customerName;
  }

  /**
   * @return the featuresTypes
   */
  public FeaturesCategoryType getFeaturesCategory() {
    return featuresCategory;
  }

  public String getFeaturesType() {
    return featuresType;
  }

  /**
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TRANSACTION_ID")
  @SearchableId
  public String getId() {
    return id;
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
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((clientHostname == null) ? 0 : clientHostname.hashCode());
    result = (prime * result) + ((clientIP == null) ? 0 : clientIP.hashCode());
    result = (prime * result) + ((customerName == null) ? 0 : customerName.hashCode());
    result = (prime * result) + ((featuresCategory == null) ? 0 : featuresCategory.hashCode());
    result = (prime * result) + ((featuresType == null) ? 0 : featuresType.hashCode());
    result = (prime * result) + ((id == null) ? 0 : id.hashCode());
    result = (prime * result) + ((layerCount == null) ? 0 : layerCount.hashCode());
    result = (prime * result) + ((mapStyle == null) ? 0 : mapStyle.hashCode());
    result = (prime * result) + ((requestDate == null) ? 0 : requestDate.hashCode());
    result = (prime * result) + ((serviceHost == null) ? 0 : serviceHost.hashCode());
    result = (prime * result) + ((serviceName == null) ? 0 : serviceName.hashCode());
    result = (prime * result) + ((xcoord == null) ? 0 : xcoord.hashCode());
    result = (prime * result) + ((ycoord == null) ? 0 : ycoord.hashCode());
    result = (prime * result) + ((zoomLevel == null) ? 0 : zoomLevel.hashCode());
    return result;
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
    this.xcoord = xcoord;
  }

  /**
   * @param ycoord
   *          the ycoord to set
   */
  public void setYcoord(final String ycoord) {
    this.ycoord = ycoord;
  }

  /**
   * @param zoomLevel
   *          the zoomLevel to set
   */
  public void setZoomLevel(final String zoomLevel) {
    this.zoomLevel = zoomLevel;
  }

  @Override
  public String toString() {
    return "LogCounterFeaturesTo [id=" + id + ", serviceHost=" + serviceHost + ", serviceName="
      + serviceName + ", clientIP=" + clientIP + ", clientHostname=" + clientHostname
      + ", requestDate=" + requestDate + ", customerName=" + customerName + ", xcoord=" + xcoord
      + ", ycoord=" + ycoord + ", featuresCategory=" + featuresCategory + ", featuresType="
      + featuresType + ", zoomLevel=" + zoomLevel + ", mapStyle=" + mapStyle + ", layerCount="
      + layerCount + "]";
  }

}
