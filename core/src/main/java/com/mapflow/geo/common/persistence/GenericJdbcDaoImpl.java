package com.mapflow.geo.common.persistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mapflow.geo.common.exceptions.MapflowAppException;
import com.mapflow.geo.common.helper.dataaccess.DBHelper;

public abstract class GenericJdbcDaoImpl<T, PK extends Serializable> implements GenericDaoExtend<T, PK> {

  // private final Log log = LogFactory.getLog(getClass());
  private final Logger logger = Logger.getLogger(getClass());

  private Class<T> persistentClass;

  private Map<T, PK> elements;

  private String driver = null;

  private String user = null;

  private String password = null;

  private String url = null;

  private String datasourceName = null;

  private DBHelper dbHelper;

  public GenericJdbcDaoImpl(final Class<T> persistentClass, final String datasourceName) {
    super();
    this.persistentClass = persistentClass;
    this.datasourceName = datasourceName;
  }

  public GenericJdbcDaoImpl(final Class<T> persistentClass, final String driver, final String user,
    final String password, final String url, final DBHelper dbHelper) {
    super();
    this.persistentClass = persistentClass;
    this.driver = driver;
    this.user = user;
    this.password = password;
    this.url = url;
    this.dbHelper = dbHelper;
  }

  protected Connection conectDatabase() throws MapflowAppException {

    Connection conn = null;

    if (isConfigData()) {
      conn = dbHelper.connectDatabase(driver, user, password, url);
    }
    else {
      // TODO Look how can I retrive the jdbc resource name
      // conn =
      // dbHelper.getConnection(GlobalsHelper.getVariablePointer(getDatasourceName()).getValue());
    }

    try {
      if (logger.isDebugEnabled()) {
        logger.debug("Connecting to: " + conn.getMetaData().getURL());
      }
    }
    catch (final SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }

  public String getDatasourceName() {
    return datasourceName;
  }

  protected DBHelper getDbHelper() {
    return dbHelper;
  }

  protected String getDriver() {
    return driver;
  }

  public Map<T, PK> getElements() {
    return elements;
  }

  protected Logger getLogger() {
    return logger;
  }

  protected String getPassword() {
    return password;
  }

  public Class<T> getPersistentClass() {
    return persistentClass;
  }

  protected String getUrl() {
    return url;
  }

  protected String getUser() {
    return user;
  }

  private boolean isConfigData() {

    boolean result;

    if ((driver != null) && (user != null) && (password != null) && (url != null)) {
      result = true;
    }
    else {
      result = false;
    }

    return result;
  }

  protected void setDatasourceName(final String datasourceName) {
    this.datasourceName = datasourceName;
  }

  public void setDbHelper(final DBHelper dbHelper) {
    this.dbHelper = dbHelper;
  }

  protected void setDriver(final String driver) {
    this.driver = driver;
  }

  public void setElements(final Map<T, PK> elements) {
    this.elements = elements;
  }

  protected void setPassword(final String password) {
    this.password = password;
  }

  protected void setPersistentClass(final Class<T> persistentClass) {
    this.persistentClass = persistentClass;
  }

  protected void setUrl(final String url) {
    this.url = url;
  }

  protected void setUser(final String user) {
    this.user = user;
  }

}
