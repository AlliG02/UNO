package com.uno;

import com.uno.action.*;
import com.uno.core.models.Game;

public class GameLoop {
    private final Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    public void run() {
        new PrintTitleScreen().run(game);
        loop();
        new PrintResults().run(game);
    }
    public void loop() {
        PlayerAction action = game.currentPlayer.awaitAction();
        boolean isValid = action.validate(game);
        if (isValid) {
            action.run(game);
        } else {
            System.out.println("Invalid action");
            loop();
        }

        if (!game.currentPlayer.hasWon()) {
            new SetNextPlayer().run(game);
            loop();
        }
    }
}
