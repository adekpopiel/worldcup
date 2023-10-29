package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameValidationException;
import com.adekpopiel.worldcup.usecase.port.DateFormatter;
import com.adekpopiel.worldcup.usecase.port.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartGameTest {

    public static final String GAME_ALREADY_EXISTS = "Game already exists!";
    public static final String GAME_CANNOT_BE_NULL = "Game cannot be null";
    public static final String HOME_TEAM_NAME_HAS_TO_BE_SET = "Home team name has to be set";
    public static final String VISITORS_TEAM_NAME_HAS_TO_BE_SET = "Visitors team name has to be set";
    @InjectMocks
    private StartGame startGameUseCase;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private DateFormatter dateFormatter;

    @Test
    public void testNewGameIsPersistedInRepository() {
        //given
        Game newGame = Game.builder()
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(newGame);
        //then
        verify(gameRepository).create(any(Game.class));
    }

    @Test
    public void testStartDateIsFormatted() {
        //given
        Game newGame = Game.builder()
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(newGame);
        //then
        verify(dateFormatter).formatDate(any(Date.class));
    }

    @Test
    public void testGameIsNotNull() {
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> startGameUseCase.startGame(null));
        assertTrue(exception.getMessage().contains(GAME_CANNOT_BE_NULL));

    }
    @Test
    public void testGameHomeTeamIsNotNull() {
        Game game = Game.builder()
                .visitors("Y")
                .build();
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> startGameUseCase.startGame(game));
        assertTrue(exception.getMessage().contains(HOME_TEAM_NAME_HAS_TO_BE_SET));

    }

    @Test
    public void testGameHomeTeamIsNotEmpty() {
        Game game = Game.builder()
                .homeTeam("")
                .visitors("Y")
                .build();
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> startGameUseCase.startGame(game));
        assertTrue(exception.getMessage().contains(HOME_TEAM_NAME_HAS_TO_BE_SET));

    }

    @Test
    public void testGameVisitorsIsNotNull() {
        Game game = Game.builder()
                .homeTeam("Y")
                .build();
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> startGameUseCase.startGame(game));
        assertTrue(exception.getMessage().contains(VISITORS_TEAM_NAME_HAS_TO_BE_SET));

    }

    @Test
    public void testGameVisitorsIsNotEmpty() {
        Game game = Game.builder()
                .homeTeam("X")
                .visitors("")
                .build();
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> startGameUseCase.startGame(game));
        assertTrue(exception.getMessage().contains(VISITORS_TEAM_NAME_HAS_TO_BE_SET));

    }

    @Test
    public void testGameForCreateHasProperHomeTeamScoreSet() {
        //given
        Integer homeTeamScore = 0;
        Game gameForCreate = Game.builder()
                .homeTeam("X")
                .visitors("Y")
                .homeTeamScore(homeTeamScore)
                .build();
        //when
        startGameUseCase.startGame(gameForCreate);
        //then
        verify(gameRepository).create(argThat((Game game) -> Objects.equals(game.getHomeTeamScore(), gameForCreate.getHomeTeamScore())));
    }

    @Test
    public void testGameForCreateHasProperVisitorsScoreSet() {
        //given
        Integer visitorsTeamScore = 0;
        Game gameForCreate = Game.builder()
                .homeTeam("X")
                .visitors("Y")
                .visitorsScore(visitorsTeamScore)
                .build();
        //when
        startGameUseCase.startGame(gameForCreate);
        //then
        verify(gameRepository).create(argThat((Game game) -> Objects.equals(game.getVisitorsScore(), gameForCreate.getVisitorsScore())));
    }

    @Test
    public void testGameForCreateHasIdSet() {
        //given
        Game gameForCreate = Game.builder()
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(gameForCreate);
        //then
        verify(gameRepository).create(argThat((Game game) -> !Objects.isNull(game.getId())));
    }

    @Test
    public void testGameForCreateHasStartTimeSet() {
        //given
        String startTime = "2020-07-10 15:00:00.000";
        Game gameForCreate = Game.builder()
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        when(dateFormatter.formatDate(any(Date.class))).thenReturn(startTime);
        startGameUseCase.startGame(gameForCreate);
        //then
        verify(gameRepository).create(argThat((Game game) -> Objects.equals(game.getStartTime(), startTime)));
    }

    @Test
    public void testGameForCreateHasHomeTeamNameSet() {
        //given
        String homeTeam = "X";
        Game gameForCreate = Game.builder()
                .homeTeam(homeTeam)
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(gameForCreate);
        //then
        verify(gameRepository).create(argThat((Game game) -> Objects.equals(game.getHomeTeam(), homeTeam)));
    }

    @Test
    public void testGameForCreateHasVisitorsTeamNameSet() {
        //given
        String visitorsTeam = "Y";
        Game gameForCreate = Game.builder()
                .homeTeam("X")
                .visitors(visitorsTeam)
                .build();
        //when
        startGameUseCase.startGame(gameForCreate);
        //then
        verify(gameRepository).create(argThat((Game game) -> Objects.equals(game.getVisitors(), visitorsTeam)));
    }
    
}