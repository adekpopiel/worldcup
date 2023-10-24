package com.adekpopiel.worldcup.usecase.validation;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameValidationException;

public class GameValidator {

    private GameValidator() {

    }

    public static void validate(final Game game) {
        if (game == null) throw new GameValidationException("Game cannot be null");
        if (game.getHomeTeam() == null) throw new GameValidationException("Home team name has to be set");
        if (game.getHomeTeam().isEmpty()) throw new GameValidationException("Home team name has to be set");
        if (game.getVisitors() == null) throw new GameValidationException("Visitors team name has to be set");
        if (game.getVisitors().isEmpty()) throw new GameValidationException("Visitors team name has to be set");
    }
}
