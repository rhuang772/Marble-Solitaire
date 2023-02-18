package cs3500.marblesolitaire.controller;

/**
 * Represents an interface that has methods the controller will implement.
 * That method is playGame().
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of MarbleSolitaire. Throw an exception only if controller is unable to
   * read input or transmit output.
   */
  public void playGame();
}
