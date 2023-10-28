package com.adekpopiel.worldcup.controller;

import com.adekpopiel.worldcup.controller.model.GameDto;
import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.FinishGame;
import com.adekpopiel.worldcup.usecase.PrintGame;
import com.adekpopiel.worldcup.usecase.StartGame;
import com.adekpopiel.worldcup.usecase.UpdateGame;

import java.util.UUID;

public class GameController {

    private final StartGame startGameUseCase;
    private final UpdateGame updateGameUseCase;

    private final FinishGame finishGameUseCase;

    private final PrintGame printGameUseCase;

    public GameController(final StartGame startGameUseCase,
                          final UpdateGame updateGameUseCase,
                          final FinishGame finishGameUseCase,
                          final PrintGame printGameUseCase) {
        this.startGameUseCase = startGameUseCase;
        this.updateGameUseCase = updateGameUseCase;
        this.finishGameUseCase = finishGameUseCase;
        this.printGameUseCase = printGameUseCase;
    }

    public GameDto startGame(final GameDto inputGame) {
        if (inputGame == null) {
            throw new IllegalArgumentException("Game cannot be null!");
        }
        Game game = inputGame.toGame();
        return GameDto.toGameDto(startGameUseCase.startGame(game));
    }

    public void updateGame(final UUID gameId, Integer homeTeamScore, Integer visitorsScore) {
        updateGameUseCase.updateGame(gameId, homeTeamScore, visitorsScore);
    }

    public void finishGame(final UUID gameIdToFinish) {
        finishGameUseCase.finishGame(gameIdToFinish);
    }

    public void printScoreboard() {
        printGameUseCase.printScoreboard();
    }


}
