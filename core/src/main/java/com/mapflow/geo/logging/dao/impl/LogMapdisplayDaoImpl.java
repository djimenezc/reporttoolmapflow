package com.mapflow.geo.logging.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mapflow.geo.exceptions.MapflowAppException;
import com.mapflow.geo.helper.dataaccess.DBHelper;
import com.mapflow.geo.logging.dao.AbstractLogDaoImpl;
import com.mapflow.geo.logging.dao.LogMapdisplayDao;
import com.mapflow.geo.logging.model.LogMapdisplayTo;

/**
 * DAO to manage the CRUD operation with Log entries
 * 
 * @author djimenez
 */
public class LogMapdisplayDaoImpl extends AbstractLogDaoImpl<LogMapdisplayTo, String> implements
  LogMapdisplayDao {

  private final Logger logger = Logger.getLogger(getClass());

  public LogMapdisplayDaoImpl(final Class<LogMapdisplayTo> persistentClass,
    final String datasourceName, final DBHelper dbHelper) {

    super(persistentClass, datasourceName);
    setDbHelper(dbHelper);
  }

  public LogMapdisplayDaoImpl(final Class<LogMapdisplayTo> persistentClass, final String driver,
    final String user, final String password, final String url, final DBHelper dbHelper) {

    super(persistentClass, driver, user, password, url, dbHelper);
  }

  @Override
  public boolean exists(final String id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<LogMapdisplayTo> findByNamedQuery(final String queryName,
    final Map<String, Object> queryParams) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LogMapdisplayTo findFirst() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LogMapdisplayTo get(final String id) {
    // TODO Auto-generated method stub
    final LogMapdisplayTo log = new LogMapdisplayTo();

    return log;
  }

  @Override
  public List<LogMapdisplayTo> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<LogMapdisplayTo> getAllDistinct() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * A utility function for logging and debugging.
   * 
   * @return A string value describing this instance of the service logger.
   */
  public String logInfo() {

    final String description = "";
    // '"' + this.getRequestDateString() + '"' + ',' + '"' + this.trans.getID() + '"' + ',' + '"'
    // + this.clientIP + '"' + ',' + '"' + this.serviceName + '"' + ',' + '"'
    // + this.serviceHostName + '"' + ',' + '"'
    // + ((this.ticket == null) ? "" : this.ticket.getLogInfo()) + '"' + ',' + '"' + this.duration
    // + '"';

    return description;
  }

  @Override
  public void remove(final LogMapdisplayTo entity) {
    // TODO Auto-generated method stub

  }

  @Override
  public void remove(final String entity) {
    // TODO Auto-generated method stub

  }

  @Override
  public LogMapdisplayTo save(final LogMapdisplayTo log) {

    Connection conn = null;
    LogMapdisplayTo result = null;

    try {
      conn = conectDatabase();
    }
    catch (final MapflowAppException e) {
      logger.error(e.getMessage());
    }

    if (conn != null) {
      try {

        log.setId(getSequenceNumber(conn));

        int idx = 1;
        final PreparedStatement prest = conn.prepareStatement(pstmt);

        prest.setString(idx++, log.getId());
        prest.setString(idx++, log.getServiceHost());
        prest.setString(idx++, log.getServiceName());
        prest.setString(idx++, log.getTicket());
        prest.setString(idx++, log.getClientIP());
        prest.setTimestamp(idx++, new java.sql.Timestamp(log.getRequestDate().getTime()));
        prest.setString(idx++, log.getClientHostname());
        prest.setInt(idx++, log.getDuration());

        final String userField0 = log.getCustomerName() + ":" + log.getLayer();
        prest.setString(idx++, userField0);
        prest.setString(idx++, log.getXcoord());
        prest.setString(idx++, log.getYcoord());
        prest.setString(idx++, log.getImageHeight());
        prest.setString(idx++, log.getImageWidth());
        prest.setString(idx++, log.getMimeType());
        prest.setString(idx++, log.getZoomLevel());
        prest.setString(idx++, log.getMapStyle());
        prest.setString(idx++, log.getLayerCount());
        prest.setString(idx++, log.getUSER_FIELD_9());

        prest.executeUpdate();
        if (logger.isDebugEnabled()) {

          logger.debug(log.toString());
        }

        result = log;
        conn.close();
      }
      catch (final SQLException s) {
        logger.error("Error inserting log data " + "[" + s.getMessage() + "]");
      }
      finally {
        // close the connection...
        try {
          if (conn != null) {
            conn.close();
          }
        }
        catch (final Exception ignore) {
        }
        conn = null;
      }
    }

    return result;
  }

}
