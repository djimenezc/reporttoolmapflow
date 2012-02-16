package com.mapflow.geo.test.reporting.unitary.service;

import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {
// "classpath:/spring/persistance/applicationContext-test-datasources.xml" })
public abstract class JasperPrintTest {

  private Map<String, Object> data;

  // @Autowired
  private DataSource dataSource;
  protected Connection connection = null;

  /**
   * @return the connection
   */
  public Connection getConnection() {
    return connection;
  }

  public Map<String, Object> getData() {
    return data;
  }

  /**
   * @return the dataSource
   */
  public DataSource getDataSource() {
    return dataSource;
  }

  public void setData(final Map<String, Object> data) {
    this.data = data;
  }

  /**
   * @param dataSource
   *          the dataSource to set
   */
  public void setDataSource(final DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Before
  public void setUp() throws Exception {
    connection = dataSource.getConnection();
  }
}
