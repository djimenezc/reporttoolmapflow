package com.mapflow.geo.test.reporting.unitary.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mapflow.geo.reporting.report.JasperReportImpl;

public class FactoryReportTestObjects {

  public static JasperReportImpl buildAxaReport() {

    final JasperReportImpl report = new JasperReportImpl();

    report.setTemplateName("monthly_all.jrxml");

    final Map<String, Object> parameters = new HashMap<String, Object>();

    report.setParameters(parameters);

    final List<DataSource> datasources = new ArrayList<DataSource>();

    report.setDatasources(datasources);

    // TODO Fill datasources and parameters

    return report;
  }

  public static JasperReportImpl buildExposurePolicySummaryReport() {

    final JasperReportImpl report = new JasperReportImpl();
    final String currentDir = System.getProperty("user.dir");

    report.setTemplateName("exposurePolicySummary.jrxml");

    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("AVG_SI", "1111");
    parameters.put("NUM_POLICIES", 12);
    parameters.put("TOTAL_SI", "150");
    parameters.put("DATASETS", "UK");
    parameters.put("POLICY_TYPE", "Comercial");
    parameters.put("AREA", "12111 Km2");
    final String pathMapImage = currentDir + "";
    parameters.put("PATH_MAP_IMAGE", pathMapImage);
    final String pathImages = currentDir + "/src/test/images/logo/logo.png";
    parameters.put("LOGO_PATH", pathImages);

    report.setParameters(parameters);

    final List<DataSource> datasources = new ArrayList<DataSource>();

    report.setDatasources(datasources);

    // TODO Fill datasources and parameters

    return report;
  }
}
