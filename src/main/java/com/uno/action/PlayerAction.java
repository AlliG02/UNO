package com.uno.action;

import com.uno.core.models.Game;

public abstract class PlayerAction implements Action {
    public abstract boolean validate(Game game);
}
