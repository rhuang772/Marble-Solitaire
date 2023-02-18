package cs3500.marblesolitaire.view;

import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.controller.CorruptedAppendable;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Represents the methods tested for all TriangleSolitaireTextView objects.
 * They take in methods inherited from MarbleSolitaireModelState.
 */
public class TriangleSolitaireTextViewTest {

  private MarbleSolitaireView tsv1;

  private MarbleSolitaireView tsv2;

  private Appendable ap;

  private void init() {
    this.tsv1 = new TriangleSolitaireTextView(new TriangleSolitaireModel(), ap);
    this.tsv2 = new TriangleSolitaireTextView(new TriangleSolitaireModel());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid() {
    this.tsv1 = new TriangleSolitaireTextView(null);
    this.tsv2 = new TriangleSolitaireTextView(null, null);
  }

  @org.junit.Test
  public void testToString() {
    TriangleSolitaireModel m1 = new TriangleSolitaireModel();

    TriangleSolitaireModel m2 = new TriangleSolitaireModel(5, 4,2);
    m2.move(4,4,4,2);

    this.tsv1 = new TriangleSolitaireTextView(m1);
    this.tsv2 = new TriangleSolitaireTextView(m2);
    TriangleSolitaireTextView tsv3 = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(7));
    TriangleSolitaireTextView tsv4 = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(6, 3, 1));

    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", this.tsv1.toString());
    assertEquals("      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", tsv3.toString());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O _ _", this.tsv2.toString());
    assertEquals("    O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O _ O O\n" +
            " O O O O O\n" +
            "O O O O O O", tsv4.toString());
  }

  @org.junit.Test (expected = IOException.class)
  public void renderBoard() throws IOException {
    this.tsv1 = new TriangleSolitaireTextView(new TriangleSolitaireModel(),
            new CorruptedAppendable());
    this.tsv1.renderBoard();
  }

  @Test (expected = IOException.class)
  public void testRenderMessage() throws IOException {
    this.tsv1 = new MarbleSolitaireTextView(new TriangleSolitaireModel(),
            new CorruptedAppendable());
    this.tsv1.renderMessage("Invalid blah");
  }
}