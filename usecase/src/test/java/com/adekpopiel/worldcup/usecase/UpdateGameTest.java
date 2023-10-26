package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameAlreadyExistException;
import com.adekpopiel.worldcup.usecase.exception.GameNotFoundException;
import com.adekpopiel.worldcup.usecase.port.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateGameTest {

    @InjectMocks
    UpdateGame updateGameUseCase;

    @Mock
    GameRepository gameRepository;

    @Test
    public void testGameIsUpdatedInRepository() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        Game originalGame = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(originalGame);
        updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, visitorsScoreForUpdate);
        //then
        verify(gameRepository).update(any(Game.class));
    }

    @Test
    public void testGameForUpdateHasProperHomeTeamScoreSet() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeamScore(2)
                .visitorsScore(2)
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForUpdate);
        updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, visitorsScoreForUpdate);
        //then
        verify(gameRepository).update(argThat((Game game) -> Objects.equals(game.getHomeTeamScore(), gameForUpdate.getVisitorsScore())));
    }

    @Test
    public void testGameForUpdateHasProperVisitorsScoreSet() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeamScore(2)
                .visitorsScore(2)
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForUpdate);
        updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, visitorsScoreForUpdate);
        //then
        verify(gameRepository).update(argThat((Game game) -> Objects.equals(game.getVisitorsScore(), gameForUpdate.getVisitorsScore())));
    }

    @Test
    public void testGameForUpdateHasProperIdSet() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeamScore(2)
                .visitorsScore(2)
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForUpdate);
        updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, visitorsScoreForUpdate);
        //then
        verify(gameRepository).update(argThat((Game game) -> Objects.equals(game.getId(), gameId)));
    }

    @Test
    public void testGameForUpdateHasStartTimeUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeamScore(2)
                .visitorsScore(2)
                .startTime("2020-07-10 15:00:00.000")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForUpdate);
        updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, visitorsScoreForUpdate);
        //then
        verify(gameRepository).update(argThat((Game game) -> Objects.equals(game.getStartTime(), gameForUpdate.getStartTime())));
    }

    @Test
    public void testGameForUpdateHasHomeTeamNameUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeamScore(2)
                .visitorsScore(2)
                .homeTeam("X")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForUpdate);
        updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, visitorsScoreForUpdate);
        //then
        verify(gameRepository).update(argThat((Game game) -> Objects.equals(game.getHomeTeam(), gameForUpdate.getHomeTeam())));
    }

    @Test
    public void testGameForUpdateHasVisitorsTeamNameUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeamScore(2)
                .visitorsScore(2)
                .homeTeam("Y")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForUpdate);
        updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, visitorsScoreForUpdate);
        //then
        verify(gameRepository).update(argThat((Game game) -> Objects.equals(game.getVisitors(), gameForUpdate.getVisitors())));
    }

    @Test
    public void testThrowsGameNotFoundExceptionIfGameNotFound() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        //when
        assertThrows(
                GameNotFoundException.class,
                () -> updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, visitorsScoreForUpdate));
    }
    @Test
    public void testThrowsIllegalArgumentExceptionIfGameIdIsNull() {
        //given
        Integer homeTeamScoreForUpdate = 2;
        Integer visitorsScoreForUpdate = 2;
        //when
        assertThrows(
                IllegalArgumentException.class,
                () -> updateGameUseCase.updateGame(null, homeTeamScoreForUpdate, visitorsScoreForUpdate));
    }

    @Test
    public void testThrowsIllegalArgumentExceptionIfHomeTeamScoreIsNull() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer visitorsScoreForUpdate = 2;
        //when
        assertThrows(
                IllegalArgumentException.class,
                () -> updateGameUseCase.updateGame(gameId, null, visitorsScoreForUpdate));
    }

    @Test
    public void testThrowsIllegalArgumentExceptionIfVisitorsScoreIsNull() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScoreForUpdate = 2;
        //when
        assertThrows(
                IllegalArgumentException.class,
                () -> updateGameUseCase.updateGame(gameId, homeTeamScoreForUpdate, null));
    }

}