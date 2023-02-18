package cs3500.marblesolitaire.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Tests the methods utilized in the EnglishSolitaireModel class. The methods are from the
 * MarbleSolitaireModel interface.
 */
public class EnglishSolitaireModelTest {

  private EnglishSolitaireModel e1;

  private EnglishSolitaireModel e2;

  private EnglishSolitaireModel e3;

  private EnglishSolitaireModel e4;

  /**
   * Initializes the previous EnglishSolitaireModel examples defined. They come in 4 forms each
   * defined by the constructors in the ESM class.
   */
  private void init() {
    this.e1 = new EnglishSolitaireModel();
    this.e2 = new EnglishSolitaireModel(3, 3);
    this.e3 = new EnglishSolitaireModel(3, 0, 2);
    this.e4 = new EnglishSolitaireModel(5, 0, 2);

  }

  /**
   * Tests an invalid initialization of an ESM. In this case, both the row and col positions
   * are chosen to be negative, which is impossible.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidInitialization() {
    EnglishSolitaireModel invalid = new EnglishSolitaireModel(0, 0);
    EnglishSolitaireModel invalid2 = new EnglishSolitaireModel(2);
    EnglishSolitaireModel invalid3 = new EnglishSolitaireModel(5, 0,0);
    EnglishSolitaireModel invalid4 = new EnglishSolitaireModel(6,3,3);
  }

  /**
   * Tests valid initializations of ESMs. In this case, they are all positive, valid positions.
   */
  @Test
  public void testValidInitialization() {
    EnglishSolitaireModel valid1  = new EnglishSolitaireModel(3,0,2);
    EnglishSolitaireModel valid3 = new EnglishSolitaireModel();
    EnglishSolitaireModel valid4 = new EnglishSolitaireModel(3, 3);
    EnglishSolitaireModel valid5 = new EnglishSolitaireModel(3);
    assertEquals(7, valid1.getBoardSize());
  }

  /**
   * Tests the move method in ESM. We mutate the board accordingly and test to see what
   * the modified slot looks like afterwards.
   */
  @org.junit.Test
  public void move() {
    this.e1 = new EnglishSolitaireModel();
    this.e2 = new EnglishSolitaireModel();
    this.e3 = new EnglishSolitaireModel();

    // move down 2 rows
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.e1.getSlotAt(1,3));
    e1.move(1, 3, 3,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.e1.getSlotAt(1,3));

    // move up 2 rows
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.e1.getSlotAt(4,3));
    e1.move(4,3,2,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,this.e1.getSlotAt(4,3));

    // move left 2 cols
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.e2.getSlotAt(3,5));
    e2.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.e2.getSlotAt(3,5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.e2.getSlotAt(3,3));

    // move right 2 cols
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.e2.getSlotAt(3,1));
    e3.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.e3.getSlotAt(3,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.e3.getSlotAt(3,3));


  }

  /**
   * Tests invalid moves. They throw exceptions if they are invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoves() {
    this.e3 = new EnglishSolitaireModel();

    // from position doesn't exist
    e3.move(9,-20,3,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, e3.getSlotAt(9,-20));

    // to position doesn't exist
    e3.move(3,4,9,-20);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, e3.getSlotAt(9,-20));

    // from position is not a marble AND to position is not empty
    e3.move(3,3,3,5);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, e3.getSlotAt(3,3));

    // marble jumping over an empty slot
    e3.move(3,2,3,4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, e3.getSlotAt(3,2));

    // more than 2 positions apart
    e3.move(3,2,3,5);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, e3.getSlotAt(3,5));

    // diagonal move
    e3.move(3,2, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, e3.getSlotAt(3,2));

  }

  /**
   * Tests if the game is over or not. A game is over if no more moves can be made.
   */
  @org.junit.Test
  public void isGameOver() {
    this.e1 = new EnglishSolitaireModel();
    e1.move(1, 3, 3,3);
    e1.move(4,3, 2, 3);
    assertEquals(false, this.e1.isGameOver());
  }

  /**
   * Gets the board sizes of certain ESMs. These follow the standard formula of getting the
   * board size in the getBoardSize() method.
   */
  @org.junit.Test
  public void getBoardSize() {
    this.e3 = new EnglishSolitaireModel(3,0,2);
    this.e1 = new EnglishSolitaireModel();
    this.e4 = new EnglishSolitaireModel(5);
    assertEquals(7, this.e3.getBoardSize());
    assertEquals(7, this.e1.getBoardSize());
    assertEquals(13, this.e4.getBoardSize());
  }

  /**
   * Gets the slot state at a specified position at the user's discretion. The slot states can
   * be either Marble, Empty, or Invalid.
   */
  @org.junit.Test
  public void getSlotAt() {
    this.e3 = new EnglishSolitaireModel(3,0,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.e3.getSlotAt(0,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.e3.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.e3.getSlotAt(0, 4));

  }

  /**
   * Gets the score of a certain solitaire board. These games may be in progress, or just starting
   * out.
   */
  @org.junit.Test
  public void getScore() {
    this.e3 = new EnglishSolitaireModel(3,0,2);
    this.e1 = new EnglishSolitaireModel();
    this.e4 = new EnglishSolitaireModel(5);

    assertEquals(32, this.e3.getScore());
    assertEquals(32,this.e1.getScore());
    assertEquals(104, this.e4.getScore());
  }
}