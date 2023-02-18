package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;

/**
 * The {@code MarbleSolitaireModel} represents the methods used for an EnglishSolitaireModel object.
 *
 */
public class EnglishSolitaireModel extends AbstractMarbleSolitaireModel {

  private int thickness;

  /**
   * Constructs a {@code MarbleSolitaireModel} with an arm thickness of 3 and
   * the empty slot at the center. It takes in no parameters.
   */
  public EnglishSolitaireModel() {
    this.thickness = 3;
    this.board = generateBoard(3, 3);
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with a specified empty slot at sRow
   * and sCol. It takes in two parameters.
   * @param sRow the slot row position at which the empty slot is located.
   * @param sCol the slot column position at which the empty slot is located.
   * @throws IllegalArgumentException if the specified row or column positions are invalid.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.thickness = 3;
    this.board = generateBoard(sRow, sCol);

    if (getSlotAt(sRow, sCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + ", " + sCol + ")");
    }
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with a specified arm thickness. It only takes in
   * one parameter.
   * @param armThickness the specified arm thickness.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this.thickness = armThickness;
    this.board = generateBoard((this.getBoardSize() - 1) / 2,
            (this.getBoardSize() - 1) / 2 );

    if (!(armThickness > 0 && armThickness % 2 == 1)) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
  }

  /**
   * Constructs a {@code MarbleSolitaireModel} with a specified arm thickness
   * and empty slot position at sRow and sCol. It takes in all parameters mentioned through the
   * three previous EnglishSolitaireModel constructors.
   * @param armThickness the specified arm thickness.
   * @param sRow the slot row position at which the empty slot is located.
   * @param sCol the slot column position at which the empty slot is located.
   * @throws IllegalArgumentException if the empty slot position is invalid or the arm thickness
   *         is not an odd number.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    this.thickness = armThickness;
    this.board = generateBoard(sRow, sCol);

    if (!(armThickness > 0 && armThickness % 2 == 1)
            || getSlotAt(sRow, sCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Either the arm thickness or " +
              "empty cell position is invalid.");
    }
  }

  /**
   * Generates the initial board for the game. The positions indicated in the constructor show
   * which slot will be empty.
   * @param emptyRow the row in which the empty slot occurs.
   * @param emptyCol the column in which the empty slot occurs.
   */
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
   * Creates the invalid spots on the board.
   */
  protected void createInvalidSpace() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (i < thickness - 1 && j < thickness - 1) {
          this.board[i][j] = SlotState.Invalid;
        } else if (i > (2 * thickness) - 2  && j < thickness - 1) {
          this.board[i][j] = SlotState.Invalid;
        } else if (i < thickness - 1 && j > (2 * thickness) - 2) {
          this.board[i][j] = SlotState.Invalid;
        } else if (i > (2 * thickness) - 2 && j > (2 * thickness) - 2) {
          this.board[i][j] = SlotState.Invalid;
        }
      }
    }
  }

  /**
   * Returns the size of a board. The size is typically the arm thickness
   * multiplied by 3 decreased by 2.
   * @return the size of a board.
   */
  @Override
  public int getBoardSize() {
    return (3 * this.thickness) - 2;
  }

}
