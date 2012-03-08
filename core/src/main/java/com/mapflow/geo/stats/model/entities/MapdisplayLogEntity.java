package com.mapflow.geo.stats.model.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

import com.mapflow.model.BaseObject;

@Entity
@Table(name = "MFWH_MAPDISPLAY_LOG")
@Searchable
@XmlRootElement
public class MapdisplayLogEntity extends BaseObject {

  private static final long serialVersionUID = 1L;

  private String transactionId;
  private String serviceHost;
  private String serviceName;
  private String clientIp;
  private Date requestDate;
  private BigDecimal duration;
  private Long xcoord;
  private Long ycoord;
  private int height;
  private int width;
  private String mimetype;
  private int zoomLevel;
  private String mapstyle;
  private Long layercount;
  private String featuresetList;

  public MapdisplayLogEntity() {
  }

  public MapdisplayLogEntity(final String transactionId, final String serviceHost,
    final String serviceName, final String clientIp, final Date requestDate,
    final BigDecimal duration, final Long xcoord, final Long ycoord, final int height,
    final int width, final String mimetype, final int zoomLevel, final String mapstyle,
    final Long layercount, final String featuresetList) {
    super();
    this.transactionId = transactionId;
    this.serviceHost = serviceHost;
    this.serviceName = serviceName;
    this.clientIp = clientIp;
    this.requestDate = requestDate;
    this.duration = duration;
    this.xcoord = xcoord;
    this.ycoord = ycoord;
    this.height = height;
    this.width = width;
    this.mimetype = mimetype;
    this.zoomLevel = zoomLevel;
    this.mapstyle = mapstyle;
    this.layercount = layercount;
    this.featuresetList = featuresetList;
  }

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
    final MapdisplayLogEntity other = (MapdisplayLogEntity) obj;
    if (clientIp == null) {
      if (other.clientIp != null) {
        return false;
      }
    }
    else if (!clientIp.equals(other.clientIp)) {
      return false;
    }
    if (duration == null) {
      if (other.duration != null) {
        return false;
      }
    }
    else if (!duration.equals(other.duration)) {
      return false;
    }
    if (featuresetList == null) {
      if (other.featuresetList != null) {
        return false;
      }
    }
    else if (!featuresetList.equals(other.featuresetList)) {
      return false;
    }
    if (height != other.height) {
      return false;
    }
    if (layercount == null) {
      if (other.layercount != null) {
        return false;
      }
    }
    else if (!layercount.equals(other.layercount)) {
      return false;
    }
    if (mapstyle == null) {
      if (other.mapstyle != null) {
        return false;
      }
    }
    else if (!mapstyle.equals(other.mapstyle)) {
      return false;
    }
    if (mimetype == null) {
      if (other.mimetype != null) {
        return false;
      }
    }
    else if (!mimetype.equals(other.mimetype)) {
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
    if (transactionId == null) {
      if (other.transactionId != null) {
        return false;
      }
    }
    else if (!transactionId.equals(other.transactionId)) {
      return false;
    }
    if (width != other.width) {
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
    if (zoomLevel != other.zoomLevel) {
      return false;
    }
    return true;
  }

  @Column(name = "CLIENT_IP", length = 50)
  public String getClientIp() {
    return clientIp;
  }

  @Column(name = "DURATION", precision = 22, scale = 0)
  public BigDecimal getDuration() {
    return duration;
  }

  @Column(name = "FEATURESET_LIST", length = 50)
  public String getFeaturesetList() {
    return featuresetList;
  }

  @Column(name = "HEIGHT")
  public int getHeight() {
    return height;
  }

  @Column(name = "LAYERCOUNT")
  public Long getLayercount() {
    return layercount;
  }

  @Column(name = "MAPSTYLE", length = 50)
  public String getMapstyle() {
    return mapstyle;
  }

  @Column(name = "MIMETYPE", length = 50)
  public String getMimetype() {
    return mimetype;
  }

  @Column(name = "REQUEST_DATE", length = 7)
  public Date getRequestDate() {
    return requestDate;
  }

  @Column(name = "SERVICE_HOST", length = 50)
  public String getServiceHost() {
    return serviceHost;
  }

  @Column(name = "SERVICE_NAME", length = 50)
  public String getServiceName() {
    return serviceName;
  }

  @Id
  @Column(name = "TRANSACTION_ID", length = 50)
  @SearchableId
  public String getTransactionId() {
    return transactionId;
  }

  @Column(name = "WIDTH")
  public int getWidth() {
    return width;
  }

  @Column(name = "XCOORD")
  public Long getXcoord() {
    return xcoord;
  }

  @Column(name = "YCOORD")
  public Long getYcoord() {
    return ycoord;
  }

  @Column(name = "Z_LEVEL")
  public int getZoomLevel() {
    return zoomLevel;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((clientIp == null) ? 0 : clientIp.hashCode());
    result = (prime * result) + ((duration == null) ? 0 : duration.hashCode());
    result = (prime * result) + ((featuresetList == null) ? 0 : featuresetList.hashCode());
    result = (prime * result) + height;
    result = (prime * result) + ((layercount == null) ? 0 : layercount.hashCode());
    result = (prime * result) + ((mapstyle == null) ? 0 : mapstyle.hashCode());
    result = (prime * result) + ((mimetype == null) ? 0 : mimetype.hashCode());
    result = (prime * result) + ((requestDate == null) ? 0 : requestDate.hashCode());
    result = (prime * result) + ((serviceHost == null) ? 0 : serviceHost.hashCode());
    result = (prime * result) + ((serviceName == null) ? 0 : serviceName.hashCode());
    result = (prime * result) + ((transactionId == null) ? 0 : transactionId.hashCode());
    result = (prime * result) + width;
    result = (prime * result) + ((xcoord == null) ? 0 : xcoord.hashCode());
    result = (prime * result) + ((ycoord == null) ? 0 : ycoord.hashCode());
    result = (prime * result) + zoomLevel;
    return result;
  }

  public void setClientIp(final String clientIp) {
    this.clientIp = clientIp;
  }

  public void setDuration(final BigDecimal duration) {
    this.duration = duration;
  }

  public void setFeaturesetList(final String featuresetList) {
    this.featuresetList = featuresetList;
  }

  public void setHeight(final int height) {
    this.height = height;
  }

  public void setLayercount(final Long layercount) {
    this.layercount = layercount;
  }

  public void setMapstyle(final String mapstyle) {
    this.mapstyle = mapstyle;
  }

  public void setMimetype(final String mimetype) {
    this.mimetype = mimetype;
  }

  public void setRequestDate(final Date requestDate) {
    this.requestDate = requestDate;
  }

  public void setServiceHost(final String serviceHost) {
    this.serviceHost = serviceHost;
  }

  public void setServiceName(final String serviceName) {
    this.serviceName = serviceName;
  }

  public void setTransactionId(final String transactionId) {
    this.transactionId = transactionId;
  }

  public void setWidth(final int width) {
    this.width = width;
  }

  public void setXcoord(final Long xcoord) {
    this.xcoord = xcoord;
  }

  public void setYcoord(final Long ycoord) {
    this.ycoord = ycoord;
  }

  public void setZoomLevel(final int zoomLevel) {
    this.zoomLevel = zoomLevel;
  }

  @Override
  public String toString() {
    return "MapdisplayLog [transactionId=" + transactionId + ", serviceHost=" + serviceHost
      + ", serviceName=" + serviceName + ", clientIp=" + clientIp + ", requestDate=" + requestDate
      + ", duration=" + duration + ", xcoord=" + xcoord + ", ycoord=" + ycoord + ", height="
      + height + ", width=" + width + ", mimetype=" + mimetype + ", zoomLevel=" + zoomLevel
      + ", mapstyle=" + mapstyle + ", layercount=" + layercount + ", featuresetList="
      + featuresetList + "]";
  }

}
