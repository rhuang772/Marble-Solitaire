package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;


/**
 * Tests the methods utilized in the TriangleSolitaireModel class. The methods are from the
 * MarbleSolitaireModel interface, and methods extended from the abstract marble solitaire class.
 */
public class TriangleSolitaireModelTest {

  private TriangleSolitaireModel t1;

  private TriangleSolitaireModel t2;

  private TriangleSolitaireModel t3;

  private TriangleSolitaireModel t4;

  /**
   * Initializes the previous 4 defined TSMs. It comes in 4 forms later described
   * by the constructors below.
   */
  private void init() {
    this.t1 = new TriangleSolitaireModel();
    this.t2 = new TriangleSolitaireModel(5);
    this.t3 = new TriangleSolitaireModel(3,1);
    this.t4 = new TriangleSolitaireModel(6, 3, 1);

  }


  /**
   * Tests invalid initializations of TriangleSolitaireModels. They come in 3 forms
   * described by the constructors in its respective class.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidInitialization() {
    TriangleSolitaireModel invalid1 = new TriangleSolitaireModel(0);
    TriangleSolitaireModel invalid2 = new TriangleSolitaireModel(-1, 1);
    TriangleSolitaireModel invalid3 = new TriangleSolitaireModel(0, -1, 1);
  }


  /**
   * Tests valid initializations of TriangleSolitaireModels. They come in 3 forms
   * described by the constructors in its respective class.
   */
  @Test
  public void testValidInitialization() {
    TriangleSolitaireModel valid1 = new TriangleSolitaireModel(5);
    TriangleSolitaireModel valid2 = new TriangleSolitaireModel(4,2);
    TriangleSolitaireModel valid3 = new TriangleSolitaireModel(6, 3, 1);
    assertEquals(6,valid3.getBoardSize());
  }


  /**
   * Tests the move method in TriangleSM. We mutate the board accordingly and test to see what
   * the modified slot looks like afterwards.
   */
  @Test
  public void move() {
    this.t1 = new TriangleSolitaireModel(5,4,2);
    this.t2 = new TriangleSolitaireModel(5,2,0);
    this.t3 = new TriangleSolitaireModel(5, 2, 2);
    this.t4 = new TriangleSolitaireModel(5,4,2);
    TriangleSolitaireModel t5 = new TriangleSolitaireModel(5,4,2);

    // left 2 cols
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.t1.getSlotAt(4,4));
    t1.move(4,4,4,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.t1.getSlotAt(4,4));

    // up two rows left
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.t2.getSlotAt(2,0));
    t2.move(4,2,2,0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.t2.getSlotAt(2,0));

    // up two rows right
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.t3.getSlotAt(2,2));
    t3.move(4,2,2,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.t3.getSlotAt(2,2));

    // down two rows left
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.t4.getSlotAt(4,2));
    t4.move(2,0,4,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.t4.getSlotAt(4,2));

    // down two rows right
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, t5.getSlotAt(4,2));
    t5.move(2,2,4,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, t5.getSlotAt(4,2));

  }

  /**
   * Tests invalid moves for TriangleSolitaireModel. They throw exceptions if they are invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoves() {
    this.t1 = new TriangleSolitaireModel(5,4,2);

    // to position doesn't exist
    t1.move(4,1,99,90);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, t1.getSlotAt(99,90));

    // from position doesn't exist
    t1.move(99,90,4,1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, t1.getSlotAt(99,90));

    // jumping over empty slot
    t1.move(4,0,4,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, t1.getSlotAt(4,0));

    // from position is not a marble AND to position is not empty
    t1.move(4,2,4,4);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, t1.getSlotAt(4,4));

    // more than 2 positions apart diagonally
    t1.move(1,0,4,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, t1.getSlotAt(1,0));

  }

  /**
   * Tests if the game is over or not. A game is over if no more moves can be made.
   * One example is when a game isn't over after a certain amount of moves, and the other
   * is a game played until no more moves possible.
   */
  @Test
  public void isGameOver() {
    // game is not over
    this.t1 = new TriangleSolitaireModel(5,4,2);
    assertEquals(false, t1.isGameOver());
    t1.move(4,4,4,2);
    assertEquals(false, t1.isGameOver());

    // game is over
    this.t2 = new TriangleSolitaireModel();
    assertEquals(false, t2.isGameOver());
    t2.move(2,2,0,0);
    t2.move(3,1,1,1);
    t2.move(0,0,2,2);
    t2.move(3,3,1,1);
    t2.move(2,0,0,0);
    t2.move(0,0,2,2);
    t2.move(4,0,2,0);
    t2.move(4,2,4,0);
    t2.move(4,3,2,1);
    assertEquals(true, t2.isGameOver());
  }


  /**
   * Gets the board sizes of certain TSMs. These follow the standard formula of getting the
   * board size in the getBoardSize() method.
   */
  @Test
  public void getBoardSize() {
    this.t1 = new TriangleSolitaireModel();
    this.t2 = new TriangleSolitaireModel(7);
    this.t3 = new TriangleSolitaireModel(6,3,2);
    assertEquals(5, t1.getBoardSize());
    assertEquals(7, t2.getBoardSize());
    assertEquals(6, t3.getBoardSize());
  }


  /**
   * Gets the score of certain Triangle solitaire boards. Games may be in progress, or just starting
   * out.
   */
  @org.junit.Test
  public void getScore() {
    this.t1 = new TriangleSolitaireModel(5,4,2);
    assertEquals(14, t1.getScore());
    t1.move(4,4,4,2);
    assertEquals(13, t1.getScore());

    this.t2 = new TriangleSolitaireModel();
    assertEquals(14, t2.getScore());
    t2.move(2,2,0,0);
    t2.move(3,1,1,1);
    t2.move(0,0,2,2);
    t2.move(3,3,1,1);
    t2.move(2,0,0,0);
    t2.move(0,0,2,2);
    t2.move(4,0,2,0);
    t2.move(4,2,4,0);
    t2.move(4,3,2,1);
    assertEquals(5, t2.getScore());
  }
}