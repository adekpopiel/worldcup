package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameNotFoundException;
import com.adekpopiel.worldcup.usecase.port.GameRepository;

import java.util.UUID;

public class UpdateGame {

    private GameRepository gameRepository;

    public void updateGame(final UUID gameId, Integer homeTeamScore, Integer visitorsScore) {
        if (gameId == null || homeTeamScore == null || visitorsScore == null) {
            throw new IllegalArgumentException(
                    "All parameters shouldn't be null: gameId: " + gameId
                            + " homeTeamScore: " + homeTeamScore
                            + " visitorsScore: " + visitorsScore);
        }
        Game originalGame = gameRepository.findGameById(gameId);
        if (originalGame == null) {
            throw new GameNotFoundException("Game with the gameId: " + gameId + " was not found!");
        }
        Game gameForUpdate = Game.builder()
                .id(originalGame.getId())
                .homeTeamScore(homeTeamScore)
                .visitorsScore(visitorsScore)
                .homeTeam(originalGame.getHomeTeam())
                .visitors(originalGame.getVisitors())
                .startTime(originalGame.getStartTime())
                .build();
        gameRepository.update(gameForUpdate);
    }

}
