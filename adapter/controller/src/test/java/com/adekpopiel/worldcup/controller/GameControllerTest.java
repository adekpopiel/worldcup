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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    private static final String GAME_CANNOT_BE_NULL = "Game cannot be null!";

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
        Game expectedGame = Game.builder().build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(any(Game.class));
    }

    @Test
    public void testGameForStartHasNoId() {
        //given
        UUID gameId = UUID.randomUUID();
        GameDto newGame = new GameDto();
        Game expectedGame = Game.builder().id(gameId).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getId(), null)));
    }

    @Test
    public void testGameStartedHasProperId() {
        //given
        UUID gameId = UUID.randomUUID();
        GameDto newGame = new GameDto();
        newGame.setId(gameId);
        Game expectedGame = Game.builder().id(gameId).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        GameDto gameReturned = gameController.startGame(newGame);
        //then
        assertEquals(gameReturned.getId(), gameId);
    }

    @Test
    public void testGameForStartHasProperHomeTeam() {
        //given
        String homeTeamName = "X";
        GameDto newGame = new GameDto();
        newGame.setHomeTeam(homeTeamName);
        Game expectedGame = Game.builder().homeTeam(homeTeamName).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getHomeTeam(), homeTeamName)));
    }

    @Test
    public void testGameStartedHasProperHomeTeam() {
        //given
        String homeTeamName = "X";
        GameDto newGame = new GameDto();
        newGame.setHomeTeam(homeTeamName);
        Game expectedGame = Game.builder().homeTeam(homeTeamName).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        GameDto gameReturned = gameController.startGame(newGame);
        //then
        assertEquals(gameReturned.getHomeTeam(), homeTeamName);
    }

    @Test
    public void testGameForStartHasProperVisitorsTeam() {
        //given
        String visitorsTeamName = "Y";
        GameDto newGame = new GameDto();
        newGame.setVisitors(visitorsTeamName);
        Game expectedGame = Game.builder().visitors(visitorsTeamName).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getVisitors(), visitorsTeamName)));
    }

    @Test
    public void testGameStartedHasProperVisitorsTeam() {
        //given
        String visitorsTeamName = "Y";
        GameDto newGame = new GameDto();
        newGame.setVisitors(visitorsTeamName);
        Game expectedGame = Game.builder().visitors(visitorsTeamName).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        GameDto gameReturned = gameController.startGame(newGame);
        //then
        assertEquals(gameReturned.getVisitors(), visitorsTeamName);
    }

    @Test
    public void testGameForStartHasProperHomeTeamScore() {
        //given
        Integer homeTeamScore = 2;
        GameDto newGame = new GameDto();
        newGame.setHomeTeamScore(homeTeamScore);
        Game expectedGame = Game.builder().homeTeamScore(homeTeamScore).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getHomeTeamScore(), homeTeamScore)));
    }

    @Test
    public void testGameStartedHasProperHomeTeamScore() {
        //given
        Integer homeTeamScore = 2;
        GameDto newGame = new GameDto();
        newGame.setHomeTeamScore(homeTeamScore);
        Integer expectedHomeTeamScore = 0;
        Game expectedGame = Game.builder().homeTeamScore(expectedHomeTeamScore).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        GameDto gameReturned = gameController.startGame(newGame);
        //then
        assertEquals(gameReturned.getHomeTeamScore(), expectedHomeTeamScore);
    }

    @Test
    public void testGameForStartHasProperVisitorsTeamScore() {
        //given
        Integer visitorsTeamScore = 3;
        GameDto newGame = new GameDto();
        newGame.setVisitorsScore(visitorsTeamScore);
        Game expectedGame = Game.builder().visitorsScore(visitorsTeamScore).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getVisitorsScore(), visitorsTeamScore)));
    }

    @Test
    public void testGameStartedHasProperVisitorsTeamScore() {
        //given
        Integer visitorsTeamScore = 3;
        GameDto newGame = new GameDto();
        newGame.setVisitorsScore(visitorsTeamScore);
        Integer expectedVisitorsScore = 0;
        Game expectedGame = Game.builder().visitorsScore(expectedVisitorsScore).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        GameDto gameReturned = gameController.startGame(newGame);
        //then
        assertEquals(gameReturned.getVisitorsScore(), expectedVisitorsScore);
    }

    @Test
    public void testGameForStartHasProperStartTime() {
        //given
        String startTime = "2020-07-10 15:00:00.000";
        GameDto newGame = new GameDto();
        newGame.setStartTime(startTime);
        Game expectedGame = Game.builder().startTime(startTime).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        gameController.startGame(newGame);
        //then
        verify(startGameUseCase).startGame(argThat((Game game) -> Objects.equals(game.getStartTime(), startTime)));
    }

    @Test
    public void testGameStartedHasProperStartTime() {
        //given
        String startTime = "2020-07-10 15:00:00.000";
        GameDto newGame = new GameDto();
        newGame.setStartTime(startTime);
        String expectedStartTime = "2023-10-29 15:00:00.000";
        Game expectedGame = Game.builder().startTime(expectedStartTime).build();
        //when
        when(startGameUseCase.startGame(any(Game.class))).thenReturn(expectedGame);
        GameDto gameReturned = gameController.startGame(newGame);
        //then
        assertEquals(gameReturned.getStartTime(), expectedStartTime);
    }

    @Test
    public void testStartGameThrowsExceptionIfInputGameIsNull() {
        //given
        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> gameController.startGame(null));
        //then
        assertEquals(exception.getMessage(), GAME_CANNOT_BE_NULL);

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