package cs3500.marblesolitaire.view;

import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.controller.CorruptedAppendable;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Represents the methods tested for all MarbleSolitaireTextView objects.
 * They take in methods inherited from MarbleSolitaireModelState.
 */
public class MarbleSolitaireTextViewTest {

  private MarbleSolitaireView msv1;

  private MarbleSolitaireView msv2;

  private MarbleSolitaireTextView msv3;

  private MarbleSolitaireTextView msv4;

  private Appendable ap;

  private void init() {
    this.msv1 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), ap);
    this.msv2 = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    this.msv3 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(11));
    this.msv4 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(), ap);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalid() {
    this.msv1 = new MarbleSolitaireTextView(null);
    this.msv2 = new MarbleSolitaireTextView(null, null);
  }

  @Test
  public void testToString() {
    EnglishSolitaireModel m1 = new EnglishSolitaireModel();
    m1.move(1,3,3,3);

    EuropeanSolitaireModel e1 = new EuropeanSolitaireModel();
    e1.move(3,1,3,3);
    e1.move(3,4,3,2);
    e1.move(5,1,3,1);
    e1.move(2,1,4,1);
    this.msv3 = new MarbleSolitaireTextView(e1);

    EuropeanSolitaireModel e2 = new EuropeanSolitaireModel(11);
    this.msv4 = new MarbleSolitaireTextView(e2);

    this.msv1 = new MarbleSolitaireTextView(m1);
    this.msv2 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5));
    MarbleSolitaireTextView msv3 = new MarbleSolitaireTextView(
            new EnglishSolitaireModel(3, 3));
    MarbleSolitaireTextView msv4 = new MarbleSolitaireTextView(
            new EnglishSolitaireModel(3,0, 2));

    assertEquals("                    O O O O O O O O O O O\n" +
            "                  O O O O O O O O O O O O O\n" +
            "                O O O O O O O O O O O O O O O\n" +
            "              O O O O O O O O O O O O O O O O O\n" +
            "            O O O O O O O O O O O O O O O O O O O\n" +
            "          O O O O O O O O O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O _ O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "          O O O O O O O O O O O O O O O O O O O O O\n" +
            "            O O O O O O O O O O O O O O O O O O O\n" +
            "              O O O O O O O O O O O O O O O O O\n" +
            "                O O O O O O O O O O O O O O O\n" +
            "                  O O O O O O O O O O O O O\n" +
            "                    O O O O O O O O O O O", this.msv4.toString());

    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O _ O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "  _ O O O O\n" +
            "    O O O", this.msv3.toString());

    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.msv1.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", this.msv2.toString());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", msv3.toString());

    assertEquals("    _ O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", msv4.toString());
  }

  @Test (expected = IOException.class)
  public void testRenderBoard() throws IOException {
    this.msv1 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), new CorruptedAppendable());
    this.msv1.renderBoard();
  }

  @Test (expected = IOException.class)
  public void testRenderMessage() throws IOException {
    this.msv1 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), new CorruptedAppendable());
    this.msv1.renderMessage("Invalid blah");
  }
}