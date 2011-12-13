package com.mapflow.geo.logging.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mapflow.geo.common.helper.dataaccess.DBHelper;
import com.mapflow.geo.common.persistence.GenericDaoImpl;

public abstract class AbstractLogDaoImpl<T, PK> extends GenericDaoImpl<T, String> {

  protected static String pstmt =
    "INSERT INTO MF_SERVICE_LOG (transaction_id,service_host,service_name,ticket,"
      + "client_ip, request_date,client_hostname, duration,user_field_0,user_field_1,user_field_2,"
      + "user_field_3, user_field_4, user_field_5,user_field_6,user_field_7,"
      + "user_field_8, user_field_9) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  protected static final String SEQ_NEXTVAL_QUERY = "select MF_TRANSACTION_SEQ.NEXTVAL from dual";

  public AbstractLogDaoImpl(final Class<T> persistentClass, final String datasourceName) {
    super(persistentClass, datasourceName);
  }

  public AbstractLogDaoImpl(final Class<T> persistentClass, final String driver, final String user,
    final String password, final String url, final DBHelper dbHelper) {

    super(persistentClass, driver, user, password, url, dbHelper);
  }

  @SuppressWarnings("unchecked")
  protected PK getSequenceNumber(final Connection conn) throws SQLException {

    ResultSet rs;
    Statement sp_stmt;

    sp_stmt = conn.createStatement();
    rs = sp_stmt.executeQuery(SEQ_NEXTVAL_QUERY);

    String sequenceId = new String();

    if (rs.next()) {
      sequenceId = rs.getString(1);
    }

    return (PK) sequenceId;
  }
}
