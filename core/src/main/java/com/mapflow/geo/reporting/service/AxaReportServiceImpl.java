package com.mapflow.geo.reporting.service;

import java.util.Map;

import com.mapflow.geo.common.service.reporting.AbstractReportService;
import com.mapflow.geo.common.service.reporting.PdfReportService;
import com.mapflow.geo.reporting.report.customer.AxaReport;

public class AxaReportServiceImpl extends AbstractReportService implements
  PdfReportService<AxaReport> {

  public static final String LOGO_PATH = "src/main/webapp/jsp/images/logo/logo.png";
  public static final String JRXML_TEMPLATE = "/policySummary.jrxml";

  @Override
  public byte[] generateReport(final String workDir, final AxaReport data,
    final Map<String, Object> parameters) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

}
