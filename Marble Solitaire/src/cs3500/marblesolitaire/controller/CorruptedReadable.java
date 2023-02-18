package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Data structure used in testing the methods renderBoard and renderMessage
 * Helps throw IOExceptions as those methods are supposed to.
 */
public class CorruptedReadable implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Exception thrown successfully");
  }
}
