package com.mapflow.geo.reporting.service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.mapflow.geo.reporting.report.Report;
import com.mapflow.geo.reporting.type.ReportExportType;

public abstract class AbstractReportService {

  private Connection connection;

  /**
   * @param fileName
   * @param pdf
   * @throws FileNotFoundException
   * @throws IOException
   */
  private void createFile(final String fileName, final byte[] pdf) throws FileNotFoundException,
    IOException {
    final FileOutputStream fileOutput = new FileOutputStream(fileName);

    final BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);

    final int sizePdf = pdf.length;

    bufferedOutput.write(pdf, 0, sizePdf);

    bufferedOutput.close();
  }

  /**
   * Export the jasper report to pdf or xml
   * 
   * @param workDir
   * @param data
   * @param reportExportType
   * @return
   * @throws Exception
   */
  protected byte[] generateReportImpl(final String workDir, final Report data,
    final ReportExportType reportExportType, final String templateFile) throws Exception {

    final JasperReport jasperReport =
      JasperCompileManager.compileReport(workDir + "/" + templateFile);

    final JRBeanCollectionDataSource datasource =
      new JRBeanCollectionDataSource(data.getDatasources());

    // fill the report
    final JasperPrint jasperprint =
      JasperFillManager.fillReport(jasperReport, data.getParameters(), datasource);

    byte[] result = null;

    switch (reportExportType) {
      case PDF:
        result = JasperExportManager.exportReportToPdf(jasperprint);
        break;
      case XML:
        result = JasperExportManager.exportReportToXml(jasperprint).getBytes();
        break;
    }

    return result;
  }

  public Connection getConnection() {
    return connection;
  }

  public void setConnection(final Connection connection) {
    this.connection = connection;
  }

  public void toPdf(final byte[] pdf, final String fileName) throws FileNotFoundException,
    IOException {

    createFile(fileName, pdf);
  }

  public void toPdf(final JasperReport jasperReport,
    final Map<String, Object> parameterNameToValue, final String fileName) throws JRException,
    IOException {

    final byte[] pdf =
      JasperRunManager.runReportToPdf(jasperReport, parameterNameToValue, getConnection());

    createFile(fileName, pdf);
  }
}
