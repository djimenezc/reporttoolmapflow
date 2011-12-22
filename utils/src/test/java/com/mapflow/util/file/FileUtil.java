package com.mapflow.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * @author f.soberon.diego
 * @version 1.0
 */
public final class FileUtil {

  private static final int BUFFER_SIZE = 256;

  public static void copy(final File from, final File to) throws IOException {
    FileChannel source = null;
    FileChannel target = null;
    try {
      source = new FileInputStream(from).getChannel();
      target = new FileOutputStream(to).getChannel();
      source.transferTo(0, source.size(), target);
    }
    finally {
      if (source != null) {
        source.close();
      }

      if (target != null) {
        target.close();
      }
    }
  }

  public static void copy(final String fileIn, final String fileOut)
    throws IOException {

    final byte[] bytes = new byte[4096];

    final OutputStream os = new FileOutputStream(new File(fileOut));

    int len;

    final InputStream input = new FileInputStream(new File(fileIn));

    while ((len = input.read(bytes)) >= 0) {

      os.write(bytes, 0, len);
    }

    input.close();
    os.close();
  }

  /**
   * if the method return 1 it has possibled delete the file <br/>
   * -1 error deleting
   * 
   * @param fileName
   * @return
   */
  public static int delete(final String fileName) {

    final File file = new File(fileName);

    if (file.delete()) {
      System.out.println("El fichero ha sido borrado satisfactoriamente");
      return 1;
    }
    else {
      System.out.println("El fichero no puede ser borrado");
      return -1;
    }
  }

  public static File[] list(final File dir, final String regex) {
    if (!dir.isDirectory()) {
      throw new IllegalArgumentException(dir.getAbsolutePath()
        + " is not a directory");
    }

    final FilenameFilter filter = new FilenameFilter() {

      public boolean accept(final File directory, final String name) {
        return name.matches(regex);
      }
    };

    return dir.listFiles(filter);
  }

  public static void move(final File from, final File to) {
    if (!from.renameTo(to)) {
      throw new FileUtilException("Could not move from "
        + from.getAbsolutePath() + " to " + to.getAbsolutePath());
    }
  }

  public static String readBytes(final InputStream inputStream, final int count)
    throws Exception {

    final byte[] buffer = new byte[BUFFER_SIZE];

    if (inputStream.read(buffer, 0, count) < count) {
      throw new FileUtilException("Expected to read " + count
        + " bytes from file but EOF");
    }

    return new String(buffer, 0, count);
  }

  private FileUtil() {
  }
}
