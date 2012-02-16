package com.mapflow.geo.reporting.service;

import org.springframework.stereotype.Service;

import com.mapflow.geo.reporting.report.JasperReportImpl;
import com.mapflow.geo.reporting.type.ReportExportType;

@Service("reportService")
public class ReportServiceImpl extends AbstractReportService implements
  ReportService<JasperReportImpl> {

  @Override
  public byte[] generateReport(final String templateFolder, final JasperReportImpl data,
    final ReportExportType reportExportType) throws Exception {

    return generateReportImpl(templateFolder, data, reportExportType, data.getTemplateName());
  }

}
