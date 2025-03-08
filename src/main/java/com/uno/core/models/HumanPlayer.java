package com.uno.core.models;

import com.uno.action.PlayerAction;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Future;

public class HumanPlayer extends Player{

    private Scanner scanner;

    public HumanPlayer(String name, Deck sharedDeck){
        super(name, sharedDeck);
        hand.fillHand();
        scanner = new Scanner(System.in);
    }

    @Override
    public final PlayerAction awaitAction() { return null; } //TODO: implement me

    @Override
    public void takeTurn(Game game) {
        // Example logic for taking a turn
        // Check if they can even pickup first
        System.out.println(name + ", it's your turn!\n");
        hand.showHand();
        System.out.println();

        boolean hasPlayableCards = hand.hasPlayableCard(deck.getTopCard());
        boolean validChoice = false;

        while (!validChoice) {
            if (hasPlayableCards) {
                System.out.println("1. Play a card");
                System.out.println("2. Pick up a card");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline

                    switch (choice) {
                        case 1:
                            System.out.println();
                            playCard(game);
                            validChoice = true;
                            break;
                        case 2:
                            pickupCard();
                            validChoice = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose either 1 or 2.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    // Handle non-integer inputs
                    System.out.println("Invalid input. Please enter a number (1 or 2).");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
            else {
                    System.out.println("You have no cards that match. You must pickup a card.");
                    pickupCard();
                    validChoice = true;
                }
            }
        }
    }
