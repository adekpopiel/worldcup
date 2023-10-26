package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameNotFoundException;
import com.adekpopiel.worldcup.usecase.port.GameRepository;

import java.util.UUID;

public class FinishGame {

    private GameRepository gameRepository;


    public void finishGame(final UUID gameId) {
        if (gameId == null) {
            throw new IllegalArgumentException("Input parameter cannot be null: gameId");
        }
        Game gameForRemoval = gameRepository.findGameById(gameId);
        if (gameForRemoval == null) {
            throw new GameNotFoundException("Game for removal was not found!");
        }
        gameRepository.remove(gameForRemoval);
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
