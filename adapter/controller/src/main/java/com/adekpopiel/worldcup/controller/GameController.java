package com.adekpopiel.worldcup.controller;

import com.adekpopiel.worldcup.usecase.FinishGame;
import com.adekpopiel.worldcup.usecase.PrintGame;
import com.adekpopiel.worldcup.usecase.StartGame;
import com.adekpopiel.worldcup.usecase.UpdateGame;

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


}
