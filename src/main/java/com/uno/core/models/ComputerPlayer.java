package com.uno.core.models;

import com.uno.action.PlayerAction;

import java.util.Random;

public class ComputerPlayer extends Player {

    private Random random;

    public ComputerPlayer(Deck sharedDeck){
        super("Computer", sharedDeck);
        hand.fillHand();
        random = new Random();
    }

    @Override
    public final PlayerAction awaitAction() { return null; } //TODO: implement me

    @Override
    public void takeTurn(Game game){
        System.out.println(name + "'s turn:");

        if (random.nextBoolean()) {
            // Example: 50% chance to play a card
            // TODO increase the chances
            playCard(game);
        }
        else {
            pickupCard();
        }
    }

    @Override
    protected void playCard(Game game) {
        boolean cardPlayed = false;

        // Automatically try to find a playable card
        for (int i = 0; i < hand.getHandSize(); i++) {
            Card selectedCard = hand.getCard(i);

            if (selectedCard.canBePlayedOn(deck.getTopCard())) {
                hand.removeCard(i);
                deck.setTopCard(selectedCard);
                System.out.println(name + " played:");
                selectedCard.showCard();
                selectedCard.play(game); // activate card affect
                cardPlayed = true;
                break;  // Exit the loop after playing a card
            }
        }

        // If no card is playable, the computer picks up a card
        if (!cardPlayed) {
            System.out.println(name + " cannot play any card and will pick up a card.");
            pickupCard();
        }
    }
}
