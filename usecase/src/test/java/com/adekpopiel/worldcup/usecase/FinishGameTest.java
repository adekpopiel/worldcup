package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FinishGameTest {

    @InjectMocks
    private FinishGame finishGameUseCase;

    @Mock
    private GameRepository gameRepository;

    @Test
    public void testGameIsRemovedFromRepository() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForRemoval = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForRemoval);
        finishGameUseCase.finishGame(gameId);
        //then
        verify(gameRepository).findGameById(gameId);
        verify(gameRepository).remove(gameForRemoval);
    }

    @Test
    public void testGameForRemovalIsNotFound() {
        //given
        UUID gameId = UUID.randomUUID();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(null);
        //then
        assertThrows(
                GameNotFoundException.class,
                () -> finishGameUseCase.finishGame(gameId));
    }

    @Test
    public void testGameForRemovalHasIdNotChanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForRemoval = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForRemoval);
        //then
        verify(gameRepository).remove(argThat((Game game) -> Objects.equals(game.getId(), gameId)));
    }

    @Test
    public void testGameForRemovalHasHomeTeamNameNotChanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String homeTeam = "X";
        Game gameForRemoval = Game.builder()
                .id(gameId)
                .homeTeam(homeTeam)
                .visitors("Y")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForRemoval);
        //then
        verify(gameRepository).remove(argThat((Game game) -> Objects.equals(game.getHomeTeam(), homeTeam)));
    }

    @Test
    public void testGameForRemovalHasVisitorsTeamNameNotChanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String visitorsTeam = "Y";
        Game gameForRemoval = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors(visitorsTeam)
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForRemoval);
        //then
        verify(gameRepository).remove(argThat((Game game) -> Objects.equals(game.getVisitors(), visitorsTeam)));
    }

    @Test
    public void testGameForRemovalHasVisitorsScoreNotChanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer visitorsScore = 3;
        Game gameForRemoval = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .visitorsScore(visitorsScore)
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForRemoval);
        //then
        verify(gameRepository).remove(argThat((Game game) -> Objects.equals(game.getVisitorsScore(), visitorsScore)));
    }

    @Test
    public void testGameForRemovalHasHomeTeamScoreNotChanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScore = 3;
        Game gameForRemoval = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .homeTeamScore(homeTeamScore)
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForRemoval);
        //then
        verify(gameRepository).remove(argThat((Game game) -> Objects.equals(game.getHomeTeamScore(), homeTeamScore)));
    }

    @Test
    public void testGameForRemovalHasStartDateNotChanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String startDate = "2020-07-10 15:00:00.000";
        Game gameForRemoval = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .startTime(startDate)
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(gameForRemoval);
        //then
        verify(gameRepository).remove(argThat((Game game) -> Objects.equals(game.getStartTime(), startDate)));
    }
}
