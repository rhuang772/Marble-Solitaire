package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents the methods used in general for all three of European, English, and Triangle
 * solitaire models. Appropriate fields are also abstracted here.
 */
public abstract class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {


  protected SlotState[][] board;

  /**
   * Utilizes the isValidMove helper to detect valid moves.
   * The method throws an exception if the helper returns false at any point in the moving
   * process.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0).
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0).
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0).
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0).
   * @throws IllegalArgumentException if the indicated move does NOT have
   *         to and from positions greater than 0 AND to and from positions smaller than
   *         the given board size AND the from position does not lead to a Marble AND the from and
   *         to positions are not two positions away from each other horizontally and vertically
   *         AND the slot between the from and to positions is NOT a marble AND the to position
   *         is not an empty slot.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move detected");
    }
    else {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[((fromRow + toRow) / 2)][((fromCol + toCol) / 2)] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
  }

  /**
   * Checks if a given move is valid. Utilizes this by checking if the neighbors of a given cell
   * satisfy the requirements listed in the assignment.
   * @param fromRow the row of where the starting marble is located.
   * @param fromCol the column of where the starting marble is located.
   * @param toRow the row of where the marble is later moved to.
   * @param toCol the column of where the marble is later moved to.
   * @return a boolean indicating if the move is valid or not.
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    int size = this.getBoardSize();
    return ((fromRow >= 0 && fromCol >= 0) && (toRow >= 0 && toCol >= 0))
            && ((fromRow < size && fromCol < size && (toRow < size && toCol < size))
            && (getSlotAt(fromRow, fromCol) == SlotState.Marble)
            && ((Math.abs(fromRow - toRow) == 2 && fromCol - toCol == 0)
            || (Math.abs(fromCol - toCol) == 2 && fromRow - toRow == 0))
            && (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == SlotState.Marble)
            && (board[toRow][toCol] == SlotState.Empty));
  }

  /**
   * Checks if there are any moves remaining in the solitaire game. If there are no valid moves
   * remaining, the method returns true, otherwise, it returns false.
   * @return a boolean indicating if the game is over or not.
   */
  @Override
  public boolean isGameOver() {
    for (int r = 0; r < this.getBoardSize(); r++) {
      for (int c = 0; c < this.getBoardSize(); c++) {
        if (board[r][c] == SlotState.Marble && this.hasMove(r, c)) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * Checks if the current state on the board, a marble, HAS a valid move to make to begin with.
   * Checks the neighbors 2 away from the given marble.
   * @param r the current row position of the given marble.
   * @param c the current column position of the given marble.
   * @return a boolean indicating if the move is valid or not.
   */
  protected boolean hasMove(int r, int c) {
    int size = this.getBoardSize();
    if (this.board[r][c] != SlotState.Marble) {
      return false;
    }
    return (r < size - 1 && this.isValidMove(r, c, r + 2, c))
            || (r > 1 && this.isValidMove(r, c, r - 2, c))
            || (c > 1 && this.isValidMove(r, c, r, c - 2))
            || (c < (size - 1) && this.isValidMove(r, c, r, c + 2));
  }


  /**
   * Gets the slot state of a cell at any part of the game. Throws an IllegalArgumentException
   * if the SlotState at the given space is invalid.
   * @param row the row of the position sought, starting at 0.
   * @param col the column of the position sought, starting at 0.
   * @return The given state of the solitaire game at the given position.
   * @throws IllegalArgumentException if the given position is out of bounds.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (invalidBoundary(row, col)) {
      throw new IllegalArgumentException("Indicated position is out of bounds");
    } else {
      return this.board[row][col];
    }
  }

  /**
   * Checks if the given position is out of bounds on the board. Checks 4 different conditions
   * to determine that.
   * @param row the row position of the specified slot.
   * @param col the column position of the specified slot.
   * @return a Boolean indicating if the position is out of bounds or not.
   */
  protected boolean invalidBoundary(int row, int col) throws IllegalArgumentException {
    return (row < 0 || col < 0 || row >= this.getBoardSize() || col >= this.getBoardSize());
  }

  /**
   * Gets the current score of the game. The score is represented by how many marbles are
   * remaining on the board.
   * @return an integer representing the score.
   */
  @Override
  public int getScore() {
    int marbleCount = 0;
    for (int r = 0; r < this.board.length; r++) {
      for (int c = 0; c < this.board[r].length; c++) {
        if (this.board[r][c] == SlotState.Marble) {
          marbleCount = marbleCount + 1;
        }
      }
    }
    return marbleCount;
  }
}
