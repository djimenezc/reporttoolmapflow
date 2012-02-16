package com.mapflow.geo.reporting.report;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class JasperReportImpl implements Report {

  private List<DataSource> datasources;

  private Map<String, Object> parameters;

  private String templateName;

  @Override
  public List<DataSource> getDatasources() {
    return datasources;
  }

  @Override
  public Map<String, Object> getParameters() {
    return parameters;
  }

  public String getTemplateName() {
    return templateName;
  }

  @Override
  public void setDatasources(final List<DataSource> datasources) {
    this.datasources = datasources;
  }

  @Override
  public void setParameters(final Map<String, Object> parameters) {
    this.parameters = parameters;
  }

  public void setTemplateName(final String templateName) {
    this.templateName = templateName;
  }

}
