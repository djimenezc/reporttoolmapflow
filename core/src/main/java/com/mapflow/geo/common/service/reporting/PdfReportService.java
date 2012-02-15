package com.mapflow.geo.common.service.reporting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

import com.mapflow.geo.reporting.report.Report;

public abstract interface PdfReportService<T extends Report> {

  byte[] generateReport(String workDir, T data, Map<String, Object> parameters) throws Exception;

  public abstract void toPdf(final byte[] pdf, final String fileName) throws FileNotFoundException,
    IOException;

  public abstract void toPdf(final JasperReport jasperReport,
    final Map<String, Object> parameterNameToValue, final String fileName) throws JRException,
    IOException;

}
