package cs3500.marblesolitaire.model.hw02;

import java.util.Objects;

/**
 * A mock of the MarbleSolitaireModel. We don't care about what is being output in each method,
 * rather, we are concerned about whether inputs are being passed in correctly, specifically
 * in the move method that is utilized with Appendables and Readables in the controller.
 */
public class MockSolitaireModel implements MarbleSolitaireModel {

  final StringBuilder log;

  /**
   * Builds a MockSolitaireModel. Takes in a log of inputs.
   * @param log takes in the inputs of the game in progress.
   */
  public MockSolitaireModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }


  /**
   * Appends what is passed into the move method into the log. They are the positions to be
   * moved from, and the positions to be moved to.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException in no cases, since it only appends what is being done to the
   *         log.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(fromRow + " " + fromCol + " " + toRow + " " + toCol);
  }

  /**
   * Appends info to the log that the method is checking if the game is over. Returns false by
   * default since we don't care about the actual result.
   * @return boolean indicating the result.
   */
  @Override
  public boolean isGameOver() {
    log.append("Checking if game is over...");
    return false;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board.
   *
   * @return the size as an integer.
   */
  @Override
  public int getBoardSize() {
    return 0;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0.
   * @param col the column of the position sought, starting at 0.
   * @return the state of the slot at the given row and column.
   * @throws IllegalArgumentException if the row or the column are beyond
   *         the dimensions of the board.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  /**
   * Appends info to the log that the method is checking the score. Returns 0 by
   * default since we don't care about the actual result.
   * @return an int indicating the result.
   */
  @Override
  public int getScore() {
    log.append("Getting score...");
    return 0;
  }
}
