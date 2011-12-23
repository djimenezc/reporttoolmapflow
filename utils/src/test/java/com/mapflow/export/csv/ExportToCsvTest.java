package com.mapflow.export.csv;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVWriter;

public class ExportToCsvTest {

  @Test
  public void testExportToCsv() throws IOException {
    final CSVWriter writer = new CSVWriter(new FileWriter("target/yourfile.csv"), '\t');

    // feed in your array (or convert your data to an array)
    final String[] entries = "first#second#third".split("#");
    writer.writeNext(entries);
    writer.close();
  }

}
