package cs3500.marblesolitaire.model.hw04;


/**
 * Represents a TriangleSolitaireModel board/game. Methods in here
 * extend those from AbstractMarbleSolitaireModel, since they are shared among two other
 * related classes.
 */
public class TriangleSolitaireModel extends AbstractMarbleSolitaireModel {

  private int thickness;

  /**
   * Creates a default TSM with thickness of 5 and empty position at the top.
   */
  public TriangleSolitaireModel() {
    this.thickness = 5;
    this.board = generateBoard(0,0);
  }

  /**
   * Creates a TSM with the given thickness and an empty position at the top.
   * The thickness must be a number greater than 0.
   * @param triLength the length of the bottom-most section of the triangle board.
   * @throws IllegalArgumentException if the triLength is less than or equal to 0.
   */
  public TriangleSolitaireModel(int triLength) throws IllegalArgumentException {

    if (triLength <= 0) {
      throw new IllegalArgumentException("Triangle board dimension invalid.");
    }

    this.thickness = triLength;
    this.board = generateBoard(0,0);

  }

  /**
   * Creates a TSM with thickness 5 and an empty position at the specified row and col.
   * The row and col cannot correspond with an Invalid slot state.
   * @param row the row of the empty slot.
   * @param col the col of the empty slot.
   * @throws IllegalArgumentException if the specified position of the empty slot
   *         corresponds with an Invalid slot state.
   */
  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {

    this.thickness = 5;
    this.board = generateBoard(row, col);

    if (getSlotAt(row, col) == SlotState.Invalid) {
      throw new IllegalArgumentException("Specified empty position invalid.");
    }

  }

  /**
   * Creates a TSM with a specified thickness and empty position at the the row and column.
   * The row and col cannot correspond with an Invalid slot state, nor can the thickness be
   * less than or equal to 0.
   * @param triLength the length of the bottom-most section of the triangle board.
   * @param row the row of the empty position.
   * @param col the col of the empty position.
   * @throws IllegalArgumentException if the row and col correspond with an Invalid slot state, or
   *         the thickness is less than or equal to 0.
   */
  public TriangleSolitaireModel(int triLength, int row, int col) throws IllegalArgumentException {

    this.thickness = triLength;
    this.board = generateBoard(row, col);

    if (getSlotAt(row, col) == SlotState.Invalid) {
      throw new IllegalArgumentException("Specified empty position invalid.");
    }
  }

  protected SlotState[][] generateBoard(int emptyRow, int emptyCol) {

    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        this.board[row][col] = SlotState.Marble;
      }
    }
    this.board[emptyRow][emptyCol] = SlotState.Empty;
    this.createInvalidSpace();
    return board;
  }

  protected void createInvalidSpace() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (j > i || i < 0 || j < 0 || i >= this.getBoardSize() || j >= this.getBoardSize()) {
          this.board[i][j] = SlotState.Invalid;
        }
      }
    }
  }

  /**
   * Gets the thickness of a TSM. The thickness must be a positive number.
   * @return int representing the thickness of the given TSM.
   */
  @Override
  public int getBoardSize() {
    return thickness;
  }

  /**
   * Checks if a move on a TriangleSolitaireModel game is valid or not, including diagonal moves.
   * @param fromRow the row of where the starting marble is located.
   * @param fromCol the column of where the starting marble is located.
   * @param toRow the row of where the marble is later moved to.
   * @param toCol the column of where the marble is later moved to.
   * @return boolean indicating if the move is valid.
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    int size = this.getBoardSize();
    return (fromRow >= 0 && fromCol >= 0) && (toRow >= 0 && toCol >= 0)
            && (fromRow < size && fromCol < size) && (toRow < size && toCol < size)
            && ((fromRow == toRow && Math.abs(fromCol - toCol) == 2) ||
            ((fromCol == toCol || Math.abs(fromCol - toCol) == 2)
            && Math.abs(fromRow - toRow) == 2)) // diagonal move
            && getSlotAt(fromRow, fromCol) == SlotState.Marble
            && getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2) == SlotState.Marble
            && getSlotAt(toRow, toCol) == SlotState.Empty;
  }

  /**
   * Checks if the current state on the TSM board, a marble, HAS a valid move to make to begin with.
   * Checks the neighbors 2 away from the given marble, including diagonal neighbors.
   * @param r the current row position of the given marble.
   * @param c the current column position of the given marble.
   * @return boolean indicating if there are any remaining valid moves to make on a board.
   */
  protected boolean hasMove(int r, int c) {
    int size = this.getBoardSize();
    if (this.board[r][c] != SlotState.Marble) {
      return false;
    }
    return isValidMove(r, c, r, c + 2)
            || isValidMove(r, c, r, c - 2)
            || isValidMove(r, c, r - 2, c - 2) // diagonal
            || isValidMove(r, c, r + 2, c + 2) // diagonal
            || isValidMove(r, c, r + 2, c)
            || isValidMove(r, c, r - 2, c);
  }
}
