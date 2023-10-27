package com.adekpopiel.worldcup.controller;

import com.adekpopiel.worldcup.usecase.FinishGame;
import com.adekpopiel.worldcup.usecase.PrintGame;
import com.adekpopiel.worldcup.usecase.StartGame;
import com.adekpopiel.worldcup.usecase.UpdateGame;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @InjectMocks
    private GameController gameController;

    @Mock
    private StartGame startGameUseCase;

    @Mock
    private UpdateGame updateGameUseCase;
    @Mock
    private FinishGame finishGameUseCase;
    @Mock
    private PrintGame printGameUseCase;

}