package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameAlreadyExistException;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        UUID gameId = UUID.randomUUID();
        Game newGame = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(newGame);
        //then
        verify(gameRepository).create(any(Game.class));
    }

    @Test
    public void testGameExistenceIsChecked() {
        //given
        UUID gameId = UUID.randomUUID();
        Game newGame = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(newGame);
        //then
        verify(gameRepository).findGameById(gameId);
    }

    @Test
    public void testStartDateIsFormatted() {
        //given
        UUID gameId = UUID.randomUUID();
        Game newGame = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(newGame);
        //then
        verify(dateFormatter).formatDate(any(Date.class));
    }

    @Test
    public void testReturnExceptionIfGameAlreadyExists() {
        //given
        UUID gameId = UUID.randomUUID();
        Game newGame = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(newGame);
        GameAlreadyExistException exception = assertThrows(GameAlreadyExistException.class, () -> startGameUseCase.startGame(newGame));
        //then
        assertTrue(exception.getMessage().contains(GAME_ALREADY_EXISTS));
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
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScore = 0;
        Game gameForCreate = Game.builder()
                .id(gameId)
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
        UUID gameId = UUID.randomUUID();
        Integer visitorsTeamScore = 0;
        Game gameForCreate = Game.builder()
                .id(gameId)
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
    public void testGameForCreateHasProperIdSet() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForCreate = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(gameForCreate);
        //then
        verify(gameRepository).create(argThat((Game game) -> Objects.equals(game.getId(), gameForCreate.getId())));
    }

    @Test
    public void testGameForCreateHasStartTimeSet() {
        //given
        UUID gameId = UUID.randomUUID();
        String startTime = "2020-07-10 15:00:00.000";
        Game gameForCreate = Game.builder()
                .id(gameId)
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
        UUID gameId = UUID.randomUUID();
        String homeTeam = "X";
        Game gameForCreate = Game.builder()
                .id(gameId)
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
        UUID gameId = UUID.randomUUID();
        String visitorsTeam = "Y";
        Game gameForCreate = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors(visitorsTeam)
                .build();
        //when
        startGameUseCase.startGame(gameForCreate);
        //then
        verify(gameRepository).create(argThat((Game game) -> Objects.equals(game.getVisitors(), visitorsTeam)));
    }
    
}