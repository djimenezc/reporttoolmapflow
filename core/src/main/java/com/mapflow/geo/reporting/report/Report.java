package com.mapflow.geo.reporting.report;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public interface Report {

  List<DataSource> getDatasources();

  Map<String, Object> getParameters();

  void setDatasources(List<DataSource> datasources);

  void setParameters(Map<String, Object> parameters);

}
