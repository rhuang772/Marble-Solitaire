package cs3500.marblesolitaire.controller;

import java.io.IOException;

/**
 * Data structure used in testing the methods renderBoard and renderMessage
 * Helps throw IOExceptions as those methods are supposed to.
 */
public class CorruptedAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Exception thrown successfully");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Exception thrown successfully");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Exception thrown successfully");
  }
}
