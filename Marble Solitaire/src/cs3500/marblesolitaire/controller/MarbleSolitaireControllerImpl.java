package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents the controller for the Marble Solitaire game.
 * Takes in a series of user inputs to allow them to play the game by themselves.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable input;


  /**
   * Constructor for making a controller for the game. It takes in a MarbleSolitaireModel,
   * MarbleSolitaireView, and a Readable.
   * @param model the model of the game.
   * @param view the toString representation of the current state of the game.
   * @param input the Readable that takes in user input and performs actions with it.
   * @throws IllegalArgumentException if anything in the model, view, or input is null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable input)
          throws IllegalArgumentException {
    this.model = model;
    this.view = view;
    this.input = input;

    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("One of the provided elements is null");
    }

  }

  // how to process 4 inputs from user:
  // use a counter to increment by one until 4 valid inputs have been reached.
  // parse those inputs onto an array list

  /**
   * Starts the game of MarbleSolitaire. It goes through processes to check if the user input is
   * valid or not.
   * @throws IllegalStateException if anything in the Readable is deemed unreadable or invalid.
   */
  @Override
  public void playGame() throws IllegalStateException {

    int validInputs = 0;

    ArrayList<Integer> parsedInputs = new ArrayList<Integer>();

    Scanner sc = new Scanner(input);

    try {
      this.view.renderBoard();
      this.view.renderMessage("\n" + "Score: " + this.model.getScore() + "\n");

    } catch (IOException e) {
      throw new IllegalStateException("Transmission of board or message failed.");
    }

    while (!this.model.isGameOver()) {


      if (!sc.hasNext()) {
        throw new IllegalStateException("No more inputs detected");
      }


      while (validInputs != 4) {
        String userInteraction;
        try {
          userInteraction = sc.next();
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("No input detected.");
        }

        try {
          // if user quits the game at any time, do this:
          if (userInteraction.equals("Q") || userInteraction.equals("q")) {
            try {
              this.view.renderMessage("Game quit!" + "\n" + "State of game when quit:" + "\n");
              this.view.renderBoard();
              this.view.renderMessage("\n" + "Score: " + this.model.getScore());
              return;
            } catch (IOException e) {
              throw new IllegalStateException("Transmission of board or message failed.");
            }

          } else if (Integer.parseInt(userInteraction) >= 1) {
            // if input is valid, increment valid input by 1 and parse input onto arraylist
            // of valid inputs
            validInputs++;
            parsedInputs.add(Integer.parseInt(userInteraction));

          } else {
            try {
              this.view.renderMessage("Invalid value detected, enter a valid value" + "\n");
            } catch (IOException e) {
              throw new IllegalStateException("Transmission of board or message failed.");
            }
          }

        } catch (NumberFormatException n) {
          //if numerical string entered is < 1, catch NumberFormatException
          try {
            this.view.renderMessage("Invalid value detected, enter a valid value.");
          } catch (IOException e) {
            throw new IllegalStateException("Transmission of board or message failed");
          }
        }
      }

      // after 4 inputs successfully detected, try moving the marble within the game:
      try {
        //try {
        this.model.move(parsedInputs.get(0) - 1, parsedInputs.get(1) - 1,
                parsedInputs.get(2) - 1, parsedInputs.get(3) - 1);

        // if positions detected are deemed invalid by the model
      } catch (IllegalArgumentException i) {
        try {
          this.view.renderMessage("Invalid positions detected. Please enter them correctly. \n");
        } catch (IOException e) {
          throw new IllegalStateException("Transmission of board or message failed.");
        }
      }
      // after 1 valid move (4 valid row/col inputs detected), render board + score
      try {
        this.view.renderBoard();
        this.view.renderMessage("\n" + "Score: " + this.model.getScore() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Transmission of board or message failed.");
      }
      validInputs = 0;
      parsedInputs = new ArrayList<Integer>();
    }

    if (model.isGameOver()) {
      try {
        this.view.renderMessage("Game over!" + "\n");
        this.view.renderBoard();
        this.view.renderMessage("\n" + "Score: " + this.model.getScore() + "\n");
        return;
      } catch (IOException e) {
        throw new IllegalStateException("Transmission of board or message failed");
      }
    }
  }
}



