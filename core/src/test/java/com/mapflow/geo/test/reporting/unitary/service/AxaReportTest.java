package com.mapflow.geo.test.reporting.unitary.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.mapflow.geo.reporting.report.Report;
import com.mapflow.geo.reporting.service.ReportService;
import com.mapflow.geo.reporting.type.ReportExportType;
import com.mapflow.geo.test.reporting.unitary.factory.FactoryReportTestObjects;
import com.mapflow.test.service.BaseManagerTestCase;

@ContextConfiguration(locations = { "classpath:/spring/applicationContext-resources.xml",
  "classpath:/spring/applicationContext-dao-mock.xml", "classpath*:/spring/applicationContext.xml",
  "classpath:**/spring/applicationContext*.xml" })
public class AxaReportTest extends BaseManagerTestCase {

  @Autowired(required = true)
  private ReportService<Report> reportService;

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testGenerateAxaReport() throws Exception {

    final Report axaReport = FactoryReportTestObjects.buildAxaReport();

    final String currentDir = System.getProperty("user.dir");
    final String pdfPath = currentDir + "/target/reportPdfExported1.pdf";
    final String workDir = currentDir + "/src/main/resources/reports";

    final byte[] pdf = reportService.generateReport(workDir, axaReport, ReportExportType.PDF);

    reportService.toPdf(pdf, pdfPath);

    assertNotNull(pdf);
  }

  @Test
  public void testGeneratePolicySummaryReport() throws Exception {

    final Report axaReport = FactoryReportTestObjects.buildExposurePolicySummaryReport();

    final String currentDir = System.getProperty("user.dir");
    final String pdfPath = currentDir + "/target/reportPdfExported2.pdf";
    final String workDir = currentDir + "/src/test/resources/reports";

    final byte[] pdf = reportService.generateReport(workDir, axaReport, ReportExportType.PDF);

    reportService.toPdf(pdf, pdfPath);

    assertNotNull(pdf);
  }

}
