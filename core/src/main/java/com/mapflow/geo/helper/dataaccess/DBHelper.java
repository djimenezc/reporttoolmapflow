/*
 * SVAccess.java
 *
 * Created on 23 July 2003, 20:15
 */

package com.mapflow.geo.helper.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mapflow.geo.exceptions.MapflowAppException;

public class DBHelper {

  private final Logger logger = Logger.getLogger(getClass());

  private static HashMap datasources = new HashMap();

  public Connection connectDatabase(final String driver, final String user, final String pass,
    final String url) throws MapflowAppException {

    Connection con = null;

    try {
      Class.forName(driver).newInstance();
      con = DriverManager.getConnection(url, user, pass);

    }
    catch (final Exception e) {
      throw new MapflowAppException("Error conecting database, " + url + " message: "
        + e.getMessage());
    }
    return con;
  }

  public Connection getConnection(final DBDataSource dbDataSource) {
    Connection c = null;
    try {
      if (getDataSource(dbDataSource.getName()) != null) {
        if ((dbDataSource.getUsername() != null) && (dbDataSource.getPassword() != null)) {
          c =
            getDataSource(dbDataSource.getName()).getConnection(dbDataSource.getUsername(),
              dbDataSource.getPassword());
        }
        else {
          c = getDataSource(dbDataSource.getName()).getConnection();
        }
      }
    }
    catch (final MapflowAppException sqe) {
      // do something
    }
    catch (final SQLException sqe) {
      System.out.println("Error establishing a connection to the database. " + "sqlErrorCode["
        + sqe.getErrorCode() + "], sqlErrorMessage" + "[" + sqe.getMessage() + "]");
    }
    return c;
  }

  public Connection getConnection(final String dbDataSourceName) {
    Connection c = null;
    try {
      if (getDataSource(dbDataSourceName) != null) {
        c = getDataSource(dbDataSourceName).getConnection();
      }
    }
    catch (final MapflowAppException sqe) {
      // do something
    }
    catch (final SQLException sqe) {
      System.out.println("Error establishing a connection to the database. " + "sqlErrorCode["
        + sqe.getErrorCode() + "], sqlErrorMessage" + "[" + sqe.getMessage() + "]");
    }
    return c;
  }

  private DataSource getDataSource(final String dataSource) throws MapflowAppException {

    DataSource ds = (DataSource) datasources.get(dataSource);

    if (ds != null) {
      return ds;
    }

    synchronized (datasources) {
      // check again incase we were waiting on the object lock and perhaps the data
      // source has already been located while we were locked out!
      ds = (DataSource) datasources.get(dataSource);
      if (ds != null) {
        return ds;
      }

      try {

        if (logger.isDebugEnabled()) {
          logger.debug("Trying to retrieve datasource: " + dataSource);
          // The Datasource object
          ds = getDatasourceFromTomcat(dataSource);
        }

        // if not we are using tomcat
        if (ds == null) {
          final InitialContext ic = new InitialContext();
          ds = (DataSource) ic.lookup(dataSource);
        }

        // add to the stored datasources
        datasources.put(dataSource, ds);
        return ds;
      }
      catch (final NamingException lx) {
        throw new MapflowAppException("Error creating datasource. " + "NamingException["
          + lx.getMessage() + "]");
      }
    }
  }

  private DataSource getDatasourceFromTomcat(final String dataSource) {

    Context envCtx;
    DataSource ds;

    try {

      final InitialContext ic = new InitialContext();
      envCtx = (Context) ic.lookup("java:comp/env");

      final String dataSourceProcessed = dataSource.split(":", 2)[1];

      ds = (DataSource) envCtx.lookup(dataSourceProcessed);
    }
    catch (final NamingException e) {
      ds = null;
      // e.printStackTrace();
    }

    return ds;
  }

  public void tidyConnection(Connection conn, Statement sp_stmt, ResultSet rs)
    throws MapflowAppException {
    // Always make sure result sets and statements are closed,
    // and the connection is returned to the pool
    if (rs != null) {
      try {
        rs.close();
      }
      catch (final SQLException sqe) {
        // logger.info("Unexpected exception closing rs: "+
        // sqe.toString() + ", sqlstate = " + sqe.getSQLState());
        // do nothing as this is tidy up
        // could do a sysout of the code...
      }
      rs = null;
    }
    if (sp_stmt != null) {
      try {
        sp_stmt.close();
      }
      catch (final SQLException sqe) {
        // logger.info("Unexpected exception closing rs: "+
        // sqe.toString() + ", sqlstate = " + sqe.getSQLState());
        // do nothing as this is tidy up
        // could do a sysout of the code...
      }
      sp_stmt = null;
    }
    if (conn != null) {
      try {
        conn.close();
      }
      catch (final SQLException sqe) {
        // logger.info("Unexpected exception closing rs: "+
        // sqe.toString() + ", sqlstate = " + sqe.getSQLState());
        // do nothing as this is tidy up
        // could do a sysout of the code...
      }
      conn = null;
    }
  }

}
