package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

/**
 * The class that represents the games of Triangle, European, and English solitaire.
 * Has a main function that starts each game with a corresponding controller.
 */
public final class MarbleSolitaire {

  /**
   * Allows the user to input commands to specify the arguments they want for a Triangle, European,
   * or English board.
   * @param args the inputs that specify the arguments for each game.
   */
  public static void main(String [] args) {
    String input = "";
    int emptyRow = 0;
    int emptyCol = 0;
    int thickness = 0;

    if (args[0].equals("european")) {
      input = args[0];
      emptyRow = 3;
      emptyCol = 3;
      thickness = 3;

    } else if (args[0].equals("english")) {
      input = args[0];
      emptyRow = 3;
      emptyCol = 3;
      thickness = 3;

    } else if (args[0].equals("triangle")) {
      input = args[0];
      emptyRow = 0;
      emptyCol = 0;
      thickness = 5;
    }

    if (args.length >= 2 && args[2].equals("-size")) {
      thickness = Integer.parseInt(args[2]);
      emptyRow = Integer.parseInt(args[2]) - 1;
      emptyCol = Integer.parseInt(args[2]) - 1;
    } else if (args.length >= 2 && args[1].equals("-hole")) {
      emptyRow = Integer.parseInt(args[2]) - 1;
      emptyCol = Integer.parseInt(args[3]) - 1;
    }

    switch (input) {
      case "english":
        MarbleSolitaireModel eng = new EnglishSolitaireModel(thickness, emptyRow, emptyCol);
        Appendable ap = System.out;
        MarbleSolitaireTextView view = new MarbleSolitaireTextView(eng, ap);
        Readable in = new InputStreamReader(System.in);
        MarbleSolitaireControllerImpl controller =
                new MarbleSolitaireControllerImpl(eng, view, in);
        controller.playGame();
        break;
      case "european":
        MarbleSolitaireModel euro = new EuropeanSolitaireModel(thickness, emptyRow, emptyCol);
        Appendable ap2 = System.out;
        MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(euro, ap2);
        Readable in2 = new InputStreamReader(System.in);
        MarbleSolitaireControllerImpl controller2 =
                new MarbleSolitaireControllerImpl(euro, view2, in2);
        controller2.playGame();
        break;
      case "triangle":
        MarbleSolitaireModel tri = new TriangleSolitaireModel(thickness, emptyRow, emptyCol);
        Appendable ap3 = System.out;
        MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(tri, ap3);
        Readable in3 = new InputStreamReader(System.in);
        MarbleSolitaireControllerImpl controller3 =
                new MarbleSolitaireControllerImpl(tri, view3, in3);
        controller3.playGame();
        break;
      default:
        break;
    }
  }
}
