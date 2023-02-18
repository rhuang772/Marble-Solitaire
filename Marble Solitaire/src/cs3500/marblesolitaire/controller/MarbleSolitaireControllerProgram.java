package cs3500.marblesolitaire.controller;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;


/**
 * Tests the actual game running within a public method.
 * Defines the materials needed for a controller, including an appendable, a model, a view,
 * and an input.
 */
public final class MarbleSolitaireControllerProgram {
  /**
   * A public method that initiates the game. It does so by calling playGame().
   * @param args the arguments the method takes in.
   */
  public static void main(String [] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = System.out;
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    Readable input = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
  }
}
