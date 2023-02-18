package cs3500.marblesolitaire.controller;

import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the different scenarios that result from playing a game. These can vary from invalid
 * inputs, to correctly making a move, to failing a game on a certain amount of moves.
 */
public class MarbleSolitaireControllerImplTest {

  @Test (expected = IllegalStateException.class)
  public void playGame1() {
    Appendable a1 = System.out;
    Readable input = new StringReader("2 4 4 4");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", a1.toString());
  }


  @Test (expected = IllegalStateException.class)
  public void testFailGame() {
    Appendable a1 = System.out;
    Readable input = new StringReader("2 4 4 4 5 4 3 4 7 4 5 4 4 6 4 4 4 3 4 5 4 1 4 3");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O _ _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 26\n" +
            "Game over!\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 26", a1);
  }



  @Test (expected = IllegalStateException.class)
  public void testInvalidInputString() {
    Appendable a1 = System.out;
    Readable input = new StringReader("hahaha");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalStateException.class)
  public void testInvalidInputNumber() {
    Appendable a1 = System.out;
    Readable input = new StringReader("-10000");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalStateException.class)
  public void testInvalidDiagonal() {
    Appendable a1 = System.out;
    Readable input = new StringReader("3 1 4 2");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalStateException.class)
  public void testInvalidHorizontal() {
    Appendable a1 = System.out;
    Readable input = new StringReader("3 1 9 1");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalStateException.class)
  public void testInvalidVertical() {
    Appendable a1 = System.out;
    Readable input = new StringReader("3 1 -1 1");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalStateException.class)
  public void testEmpty() {
    Appendable a1 = System.out;
    Readable input = new StringReader("");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullGame() {
    Appendable a1 = System.out;
    Readable input = new StringReader("");
    MarbleSolitaireModel model = null;
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullAppendable() {
    Appendable a1 = null;
    Readable input = new StringReader("");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalArgumentException.class)
  public void allNull() {
    Appendable a1 = null;
    Readable input = null;
    MarbleSolitaireModel model = null;
    MarbleSolitaireTextView view = null;
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }

  @Test (expected = IllegalStateException.class)
  public void testInvalidLetter() {
    Appendable a1 = System.out;
    Readable input = new StringReader("S");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view , input);
    controller.playGame();
  }


  @Test (expected = IllegalStateException.class)
  public void testPlayThenQuit() {
    Appendable a1 = System.out;
    Readable input = new StringReader("2 4 4 4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", a1.toString());
  }

  @Test
  public void testPlayTwoMovesThenQuit() {
    Appendable a1 = System.out;
    Readable input = new StringReader("2 4 4 4 5 4 3 4 Q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30", a1.toString());
  }

  @Test
  public void testPlayTwoInvalidThenQuit() {
    Appendable a1 = System.out;
    Readable input = new StringReader("2 4 4 4 5 -4 3 4 Q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid value detected, enter a valid value\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", a1.toString());
  }

  @Test
  public void mockTest1() {
    StringBuilder output = new StringBuilder();
    StringBuilder log = new StringBuilder();

    try {
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireModel mockModel = new MockSolitaireModel(log);
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
      Readable input = new StringReader("2 4 4 4");
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(mockModel, view, input) ;
    } catch (IllegalStateException e) {
      assertEquals("output", log.toString());
    }
  }

  @Test
  public void mockTest2() {
    StringBuilder output = new StringBuilder();
    StringBuilder log = new StringBuilder();

    try {
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireModel mockModel = new MockSolitaireModel(log);
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
      Readable input = new StringReader("xaxaxa");
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(mockModel, view, input) ;
    } catch (IllegalStateException e) {
      assertEquals("output", log.toString());
    }
  }

  @Test
  public void mockTest3() {
    StringBuilder output = new StringBuilder();
    StringBuilder log = new StringBuilder();

    try {
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireModel mockModel = new MockSolitaireModel(log);
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
      Readable input = new StringReader("2 4 4 4 5 -4 3 4 Q");
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(mockModel, view, input) ;
    } catch (IllegalStateException e) {
      assertEquals("output", log.toString());
    }
  }

  @Test
  public void mockTest4() {
    StringBuilder output = new StringBuilder();
    StringBuilder log = new StringBuilder();

    try {
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireModel mockModel = new MockSolitaireModel(log);
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
      Readable input = new StringReader("2 4 4 4 5 4 3 4 Q");
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(mockModel, view, input) ;
    } catch (IllegalStateException e) {
      assertEquals("output", log.toString());
    }
  }

  @Test
  public void mockTest5() {
    StringBuilder output = new StringBuilder();
    StringBuilder log = new StringBuilder();

    try {
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireModel mockModel = new MockSolitaireModel(log);
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
      Readable input = new StringReader("2 4 4 4 5 4 3 4 7 4 5 4 4 6 4 4 4 3 4 5 4 1 4 3");
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(mockModel, view, input) ;
    } catch (IllegalStateException e) {
      assertEquals("output", log.toString());
    }
  }

  @Test
  public void mockTest6() {
    StringBuilder output = new StringBuilder();
    StringBuilder log = new StringBuilder();

    try {
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireModel mockModel = new MockSolitaireModel(log);
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
      Readable input = new StringReader("2 4 4 4 q");
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(mockModel, view, input) ;
    } catch (IllegalStateException e) {
      assertEquals("output", log.toString());
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void mockNull() {
    StringBuilder output = new StringBuilder();
    StringBuilder log = new StringBuilder();

    try {
      MarbleSolitaireModel model = null;
      MarbleSolitaireModel mockModel = new MockSolitaireModel(log);
      MarbleSolitaireView view = null;
      Readable input = null;
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(mockModel, view, input) ;
    } catch (IllegalStateException e) {
      assertEquals("output", log.toString());
    }
  }
}


