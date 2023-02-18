package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods utilized in the EuropeanSolitaireModel class. The methods are from the
 * MarbleSolitaireModel interface, and methods extended from the abstract marble solitaire class.
 */
public class EuropeanSolitaireModelTest {

  private EuropeanSolitaireModel e1;

  private EuropeanSolitaireModel e2;

  private EuropeanSolitaireModel e3;

  private EuropeanSolitaireModel e4;

  /**
   * Initializes the above ESMs. They can come in four forms as described later by
   * the constructors.
   */
  private void init() {
    this.e1 = new EuropeanSolitaireModel();
    this.e2 = new EuropeanSolitaireModel(3);
    this.e3 = new EuropeanSolitaireModel(2,2);
    this.e4 = new EuropeanSolitaireModel(5,0,2);
  }

  /**
   * Tests invalid initializations of EuropeanSolitaireModels. They come in 3 forms
   * described by the constructors in its respective class.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidInitialization() {
    EuropeanSolitaireModel invalid1 = new EuropeanSolitaireModel(2);
    EuropeanSolitaireModel invalid2 = new EuropeanSolitaireModel(0,-1);
    EuropeanSolitaireModel invalid3 = new EuropeanSolitaireModel(0,0,-1);

  }

  /**
   * Tests valid initializations of EuropeanSolitaireModels. They come in 3 forms
   * described by the constructors in its respective class.
   */
  @Test
  public void testValidInitialization() {
    EuropeanSolitaireModel valid1 = new EuropeanSolitaireModel(3);
    EuropeanSolitaireModel valid2 = new EuropeanSolitaireModel(2,2);
    EuropeanSolitaireModel valid3 = new EuropeanSolitaireModel(5,3,2);
    assertEquals(7, valid1.getBoardSize());
  }

  /**
   * Tests the move method in EuropeanSM. We mutate the board accordingly and test to see what
   * the modified slot looks like afterwards.
   */
  @Test
  public void move() {
    this.e1 = new EuropeanSolitaireModel();

    // move right
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, e1.getSlotAt(3,1));
    e1.move(3,1,3,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, e1.getSlotAt(3,1));

    // move left
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, e1.getSlotAt(3,4));
    e1.move(3,4,3,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, e1.getSlotAt(3,4));

    // move up
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, e1.getSlotAt(5,1));
    e1.move(5,1,3,1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, e1.getSlotAt(5,1));

    // move down
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, e1.getSlotAt(2,1));
    e1.move(2,1,4,1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, e1.getSlotAt(2,1));


  }

  /**
   * Tests invalid moves for a EuropeanSolitaireModel. They throw exceptions if they are invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoves() {
    this.e3 = new EuropeanSolitaireModel();

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
   * One example is when a game isn't over after a certain amount of moves, and the other
   * is a game played until no more moves possible.
   */
  @Test
  public void isGameOver() {
    this.e1 = new EuropeanSolitaireModel();
    this.e2 = new EuropeanSolitaireModel(3);

    assertEquals(false, e1.isGameOver());
    e1.move(3,1,3,3);
    e1.move(3,4,3,2);
    e1.move(5,1,3,1);
    e1.move(2,1,4,1);
    assertEquals(false, e1.isGameOver());

    assertEquals(false, e2.isGameOver());
    e2.move(3,1,3,3);
    e2.move(3,4,3,2);
    e2.move(5,1,3,1);
    e2.move(2,1,4,1);
    e2.move(5,3,3,3);
    e2.move(5,4,3,4);
    e2.move(2,3,4,3);
    e2.move(2,4,4,4);
    e2.move(0,3,2,3);
    e2.move(0,4,2,4);
    e2.move(3,6,3,4);
    e2.move(3,4,5,4);
    e2.move(6,4,4,4);
    e2.move(5,5,3,5);
    e2.move(1,1,1,3);
    e2.move(4,3,4,5);
    e2.move(3,5,5,5);
    e2.move(1,3,3,3);
    e2.move(1,5,3,5);
    e2.move(3,2,3,4);
    e2.move(3,4,3,6);
    e2.move(6,2,6,4);
    e2.move(4,1,4,3);
    assertEquals(true, e2.isGameOver());


    MarbleSolitaireTextView viewTest = new MarbleSolitaireTextView(e2);
    System.out.println(viewTest.toString());

  }

  /**
   * Gets the board sizes of certain ESMs. These follow the standard formula of getting the
   * board size in the getBoardSize() method.
   */
  @Test
  public void getBoardSize() {
    this.e2 = new EuropeanSolitaireModel(3);
    this.e4 = new EuropeanSolitaireModel(2,2);
    this.e3 = new EuropeanSolitaireModel(11);
    assertEquals(7, this.e2.getBoardSize());
    assertEquals(7, e4.getBoardSize());
    assertEquals(31, e3.getBoardSize());
  }

  /**
   * Gets the score of certain European solitaire boards. Games may be in progress, or just starting
   * out.
   */
  @org.junit.Test
  public void getScore() {
    this.e1 = new EuropeanSolitaireModel();
    this.e2 = new EuropeanSolitaireModel(3);

    assertEquals(36, e1.getScore());
    e1.move(3,1,3,3);
    e1.move(3,4,3,2);
    e1.move(5,1,3,1);
    e1.move(2,1,4,1);
    assertEquals(32, e1.getScore());

    assertEquals(36, e2.getScore());
    e2.move(3,1,3,3);
    e2.move(3,4,3,2);
    e2.move(5,1,3,1);
    e2.move(2,1,4,1);
    e2.move(5,3,3,3);
    e2.move(5,4,3,4);
    e2.move(2,3,4,3);
    e2.move(2,4,4,4);
    e2.move(0,3,2,3);
    e2.move(0,4,2,4);
    e2.move(3,6,3,4);
    e2.move(3,4,5,4);
    e2.move(6,4,4,4);
    e2.move(5,5,3,5);
    e2.move(1,1,1,3);
    e2.move(4,3,4,5);
    e2.move(3,5,5,5);
    e2.move(1,3,3,3);
    e2.move(1,5,3,5);
    e2.move(3,2,3,4);
    e2.move(3,4,3,6);
    e2.move(6,2,6,4);
    e2.move(4,1,4,3);
    assertEquals(13, e2.getScore());
  }

}