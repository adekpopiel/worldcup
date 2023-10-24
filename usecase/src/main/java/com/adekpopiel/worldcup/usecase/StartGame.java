package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.port.DateFormatter;
import com.adekpopiel.worldcup.usecase.port.GameRepository;

public class StartGame {

    private GameRepository gameRepository;
    private DateFormatter dateFormatter;

    public Game startGame(final Game game) {
        return null;
    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public DateFormatter getDateFormatter() {
        return dateFormatter;
    }

    public void setDateFormatter(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

}
