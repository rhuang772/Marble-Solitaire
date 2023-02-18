package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * The {@code MarbleSolitaireView} represents the methods used for a MarbleSolitaireTextView object.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private MarbleSolitaireModelState msModelState;

  private Appendable ap;

  /**
   * Constructs a {@code MarbleSolitaireView} that takes in a MarbleSolitaireModelState. It inherits
   * the methods from the interface that the object represents.
   * @param msModelState the model state we use to utilize methods later on in the toString method.
   * @throws IllegalArgumentException if the provided model state is null or empty.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState msModelState)
          throws IllegalArgumentException {

    this.msModelState = msModelState;

    if (msModelState == null) {
      throw new IllegalArgumentException("The provided game model is null");
    }
  }

  /**
   * Constructs a {@code MarbleSolitaireView} that takes in a MarbleSolitaireModelState
   * and an Appendable. It inherits
   * the methods from the interface that the object represents.
   * @param msModelState the model state we use to utilize methods later on in the toString method.
   * @param ap the appendable used to take in inputs.
   * @throws IllegalArgumentException if the provided model state or appendable is null or empty.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState msModelState, Appendable ap)
          throws IllegalArgumentException {

    this.msModelState = msModelState;
    this.ap = ap;

    if (msModelState == null || ap == null) {
      throw new IllegalArgumentException("The provided game model or the Appendable is null");
    }
  }

  /**
   * Gets a visual model of how a solitaire game in progress or starting out looks like. Prints out
   * "O", "_", or " " corresponding to the slot state at a given position on the board.
   *
   * @return a String representing the game's state.
   */
  public String toString() {
    StringBuilder gameRepresentation = new StringBuilder("");
    for (int row = 0; row < this.msModelState.getBoardSize(); row++) {
      for (int col = 0; col < this.msModelState.getBoardSize(); col++) {
        if (msModelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
          gameRepresentation = gameRepresentation.append( "_ ");
        } else if (msModelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
          gameRepresentation = gameRepresentation.append( "O ");
        } else {
          gameRepresentation = gameRepresentation.append("  ");
        }
      }
      gameRepresentation = new StringBuilder(gameRepresentation.toString().stripTrailing());
      gameRepresentation = gameRepresentation.append("\n");
    }
    gameRepresentation = new StringBuilder(gameRepresentation.toString().stripTrailing());
    return gameRepresentation.toString();
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above.
   * @throws IOException if transmission of the board to the provided data destination fails.
   */
  @Override
  public void renderBoard() throws IOException {
    this.ap.append(this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted.
   * @throws IOException if transmission of the board to the provided data destination fails.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message);
  }
}
