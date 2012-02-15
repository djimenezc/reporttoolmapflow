package com.mapflow.geo.common.service.reporting;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

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
