package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameAlreadyExistException;
import com.adekpopiel.worldcup.usecase.port.DateFormatter;
import com.adekpopiel.worldcup.usecase.port.GameRepository;
import com.adekpopiel.worldcup.usecase.validation.GameValidator;

import java.util.Date;
import java.util.UUID;

public class StartGame {

    private GameRepository gameRepository;
    private DateFormatter dateFormatter;

    public Game startGame(final Game game) {
        GameValidator.validate(game);
        Game gameForCreate = Game.builder()
                .id(UUID.randomUUID())
                .homeTeam(game.getHomeTeam())
                .visitors(game.getVisitors())
                .homeTeamScore(0)
                .visitorsScore(0)
                .startTime(dateFormatter.formatDate(new Date()))
                .build();
        return gameRepository.create(gameForCreate);
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void setDateFormatter(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

}
