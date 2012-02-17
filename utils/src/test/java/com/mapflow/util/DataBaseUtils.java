package com.mapflow.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataBaseUtils {

  private final transient Log log = LogFactory.getLog(getClass());

  private final String password;
  private final String userName;
  private final String dbDriver;
  private final String dbUrl;

  /**
   * @param dbUrl
   * @param dbDriver
   * @param userName
   * @param password
   */
  public DataBaseUtils(final String dbUrl, final String dbDriver, final String userName,
    final String password) {
    this.dbUrl = dbUrl;
    this.dbDriver = dbDriver;
    this.userName = userName;
    this.password = password;
  }

  public final boolean executeQuery(final String query) throws SQLException {

    Statement sentencias = null;

    final Connection con = getConnection();

    try {

      sentencias = con.createStatement();
      sentencias.executeUpdate(query);
    }
    catch (final Exception e) {

      log.error(e.getMessage());

      return false;
    }
    finally {

      sentencias.close();
      con.close();
    }

    return true;

  }

  public final Connection getConnection() throws SQLException {

    try {
      Class.forName(dbDriver).newInstance();
    }
    catch (final ClassNotFoundException cnfe) {

      log.error(cnfe.getMessage());
      return null;
    }
    catch (final InstantiationException ie) {

      log.error(ie.getMessage());
      return null;
    }
    catch (final IllegalAccessException iae) {

      log.error(iae.getMessage());
      return null;
    }

    try {
      final Connection con = DriverManager.getConnection(this.dbUrl, userName, password);
      return con;
    }
    catch (final SQLException sqle) {

      log.error(sqle.getMessage());

      return null;
    }
  }

  public final boolean resetSchema(final String schemaName) throws SQLException {

    Statement sentencias = null;

    final Connection con = getConnection();

    try {
      sentencias = con.createStatement();
      sentencias.executeUpdate("DROP SCHEMA " + schemaName);
      sentencias.executeUpdate("CREATE SCHEMA " + schemaName);
    }
    catch (final Exception e) {

      log.error(e.getMessage());

      return false;
    }
    finally {

      sentencias.close();
      con.close();
    }

    return true;
  }

}
