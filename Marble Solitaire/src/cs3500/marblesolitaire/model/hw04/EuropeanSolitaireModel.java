package cs3500.marblesolitaire.model.hw04;


/**
 * Represents a EuropeanSolitaireModel board/game. Methods in here
 * extend those from AbstractMarbleSolitaireModel, since they are shared among two other
 * related classes.
 */
public class EuropeanSolitaireModel extends AbstractMarbleSolitaireModel {

  private int thickness;

  /**
   * Creates a default EuropeanSM with a thickness of 3 and an empty slot at the center of
   * the board.
   */
  public EuropeanSolitaireModel() {
    this.thickness = 3;
    this.board = generateBoard(3,3);
  }

  /**
   * Creates a EuropeanSM with a specified side length. It only takes in one parameter.
   * @param sideLength the specified side length.
   * @throws IllegalArgumentException if the side length is not a positive odd number.
   */
  public EuropeanSolitaireModel(int sideLength) throws IllegalArgumentException {
    this.thickness = sideLength;
    this.board = generateBoard((this.getBoardSize() - 1) / 2,
            (this.getBoardSize() - 1) / 2);

    if (!(sideLength > 0 && sideLength % 2 == 1)) {
      throw new IllegalArgumentException("Side Length invalid.");
    }
  }

  /**
   * Creates a EuropeanSM with a specified empty slot. It takes in two parameters, the row
   * and the col.
   * @param row the row of the specified empty slot.
   * @param col the col of the specified empty slot.
   * @throws IllegalArgumentException if the row and col correspond with an invalid slot state.
   */
  public EuropeanSolitaireModel(int row, int col) throws IllegalArgumentException {
    this.thickness = 3;
    this.board = generateBoard(row, col);

    if (getSlotAt(row, col) == SlotState.Invalid) {
      throw new IllegalArgumentException("Specified empty position invalid");
    }
  }

  /**
   * Creates a EuropeanSM with a specified empty slot and side length. It takes in all
   * 3 parameters mentioned in the previous three constructors.
   * @param sideLength the side length thickness of the board.
   * @param row the row of the specified empty slot.
   * @param col the col of the specified empty slot.
   * @throws IllegalArgumentException if the row and col correspond with an invalid slot state,
   *         or the side length is not a positive odd number.
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) throws IllegalArgumentException {
    this.thickness = sideLength;
    this.board = generateBoard(row, col);

    if (!(sideLength > 0 && sideLength % 2 == 1)
            || getSlotAt(row, col) == SlotState.Invalid) {
      throw new IllegalArgumentException("Side Length or Specified empty position invalid");
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

  /**
   * Creates invalid spots on a EuropeanSolitaireModel board. They are located
   * on the top left, bottom left, top right, bottom right areas.
   */
  protected void createInvalidSpace() {
    int boardSize = this.getBoardSize();
    for (int r = 0; r < boardSize; r++) {
      for (int c = 0; c < boardSize; c++) {
        if (r < this.thickness - 1 && c < ((boardSize / 2) - ((this.thickness + (2 * r)) / 2))) {
          // top left
          this.board[r][c] = SlotState.Invalid;
        } else if (r < this.thickness - 1 &&
                c > ((boardSize / 2) + ((this.thickness + (2 * r)) / 2))) {
          // top right
          this.board[r][c] = SlotState.Invalid;
        } else if (r > (boardSize / 2) + ((this.thickness + (2 * c)) / 2) &&
                c < this.thickness - 1) {
          // bottom left
          this.board[r][c] = SlotState.Invalid;
        } else if (r > thickness * 2 - 2 && c > (boardSize / 2)
                + ((this.thickness + ((boardSize - r - 1) * 2)) / 2)) {
          // bottom right
          this.board[r][c] = SlotState.Invalid;
        }
      }
    }
  }

  /**
   * Gets the size of a EuropeanSM. It may vary depend on what the user inputs in the constructor
   * for side length.
   * @return an integer representing the size of a European board.
   */
  public int getBoardSize() {
    return (3 * this.thickness) - 2;
  }
}
