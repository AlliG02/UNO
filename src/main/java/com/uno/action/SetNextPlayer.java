package com.uno.action;

import com.uno.core.models.Game;

public class SetNextPlayer implements Action {
    @Override
    public void run(Game game) {
        game.getNextPlayer();
    }
}
