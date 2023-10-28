package com.adekpopiel.worldcup.controller;

import com.adekpopiel.worldcup.controller.model.GameDto;
import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.FinishGame;
import com.adekpopiel.worldcup.usecase.PrintGame;
import com.adekpopiel.worldcup.usecase.StartGame;
import com.adekpopiel.worldcup.usecase.UpdateGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.UUID;

import static org.mockito.Mockito.*;

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


    @Test
    public void testStartGameCallsProperUseCase() {
        //given
        GameDto newGame = new GameDto();
        //when
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(any(Game.class));
    }

    @Test
    public void testGameForStartHasProperId() {
        //given
        UUID gameId = UUID.randomUUID();
        GameDto newGame = new GameDto();
        newGame.setId(gameId);
        //when
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getId(), gameId)));
    }

    @Test
    public void testGameForStartHasProperHomeTeam() {
        //given
        String homeTeamName = "X";
        GameDto newGame = new GameDto();
        newGame.setHomeTeam(homeTeamName);
        //when
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getHomeTeam(), homeTeamName)));
    }

    @Test
    public void testGameForStartHasProperVisitorsTeam() {
        //given
        String visitorsTeamName = "Y";
        GameDto newGame = new GameDto();
        newGame.setVisitors(visitorsTeamName);
        //when
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getVisitors(), visitorsTeamName)));
    }

    @Test
    public void testGameForStartHasProperHomeTeamScore() {
        //given
        Integer homeTeamScore = 2;
        GameDto newGame = new GameDto();
        newGame.setHomeTeamScore(homeTeamScore);
        //when
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getHomeTeamScore(), homeTeamScore)));
    }

    @Test
    public void testGameForStartHasProperVisitorsTeamScore() {
        //given
        Integer visitorsTeamScore = 3;
        GameDto newGame = new GameDto();
        newGame.setVisitorsScore(visitorsTeamScore);
        //when
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getVisitorsScore(), visitorsTeamScore)));
    }

    @Test
    public void testGameForStartHasProperStartTime() {
        //given
        String startTime = "2020-07-10 15:00:00.000";
        GameDto newGame = new GameDto();
        newGame.setStartTime(startTime);
        //when
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getStartTime(), startTime)));
    }

    @Test
    public void testUpdateGameCallsProperUseCase() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScore = 1;
        Integer visitorsScore = 2;
        //when
        gameController.updateGame(gameId, homeTeamScore, visitorsScore);
        //then
        verify(updateGameUseCase).updateGame(gameId, homeTeamScore, visitorsScore);
    }

    @Test
    public void testFinishGameCallsProperUseCase() {
        //given
        UUID gameId = UUID.randomUUID();
        //when
        gameController.finishGame(gameId);
        //then
        verify(finishGameUseCase).finishGame(gameId);
    }

    @Test
    public void testPrintGamesCallsProperUseCase() {
        //when
        gameController.printScoreboard();
        //then
        verify(printGameUseCase).printScoreboard();
    }
}