package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.port.GameRepository;

import java.util.UUID;

public class FinishGame {

    private GameRepository gameRepository;


    public void finishGame(final UUID gameIdForFinish) {

    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
