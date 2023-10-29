package com.adekpopiel.worldcup.db;

import com.adekpopiel.worldcup.domain.entity.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameInMemoryRepositoryTest {

    @InjectMocks
    private GameInMemoryRepository gameInMemoryRepository;

    @Mock
    private HashMap<UUID, Game> gameMap;

    @Test
    public void testCreateStoresCreatedGame() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForCreate = Game.builder()
                .id(gameId)
                .build();
        //when
        gameInMemoryRepository.create(gameForCreate);
        //then
        verify(gameMap).put(gameId, gameForCreate);
    }

    @Test
    public void testGameForCreatedHasIdUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForCreate = Game.builder()
                .id(gameId)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        gameInMemoryRepository.create(gameForCreate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getId(), gameId))
        );
    }

    @Test
    public void testGameCreatedHasIdUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForCreate = Game.builder()
                .id(gameId)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        Game gameReturned = gameInMemoryRepository.create(gameForCreate);
        //then
        assertEquals(gameReturned.getId(), gameId);
    }

    @Test
    public void testGameForCreatedHasHomeTeamUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String homeTeamName = "X";
        Game gameForCreate = Game.builder()
                .id(gameId)
                .homeTeam(homeTeamName)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        gameInMemoryRepository.create(gameForCreate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getHomeTeam(), homeTeamName))
        );
    }

    @Test
    public void testGameCreatedHomeTeamUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String homeTeamName = "X";
        Game gameForCreate = Game.builder()
                .id(gameId)
                .homeTeam(homeTeamName)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        Game gameReturned = gameInMemoryRepository.create(gameForCreate);
        //then
        assertEquals(gameReturned.getHomeTeam(), homeTeamName);
    }

    @Test
    public void testGameForCreatedHasVisitorsTeamUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String visitorsTeamName = "Y";
        Game gameForCreate = Game.builder()
                .id(gameId)
                .visitors(visitorsTeamName)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        gameInMemoryRepository.create(gameForCreate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getVisitors(), visitorsTeamName))
        );
    }

    @Test
    public void testGameCreatedHasVisitorsTeamUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String visitorsTeamName = "Y";
        Game gameForCreate = Game.builder()
                .id(gameId)
                .visitors(visitorsTeamName)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        Game gameReturned = gameInMemoryRepository.create(gameForCreate);
        //then
        assertEquals(gameReturned.getVisitors(), visitorsTeamName);
    }

    @Test
    public void testGameForCreatedHasHomeTeamScoreUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScore = 0;
        Game gameForCreate = Game.builder()
                .id(gameId)
                .homeTeamScore(homeTeamScore)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        gameInMemoryRepository.create(gameForCreate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getHomeTeamScore(), homeTeamScore))
        );
    }

    @Test
    public void testGameCreatedHasHomeTeamScoreUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScore = 0;
        Game gameForCreate = Game.builder()
                .id(gameId)
                .homeTeamScore(homeTeamScore)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        Game gameReturned = gameInMemoryRepository.create(gameForCreate);
        //then
        assertEquals(gameReturned.getHomeTeamScore(), homeTeamScore);
    }

    @Test
    public void testGameForCreateHasVisitorsTeamScoreUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer visitorsTeamScore = 0;
        Game gameForCreate = Game.builder()
                .id(gameId)
                .visitorsScore(visitorsTeamScore)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        gameInMemoryRepository.create(gameForCreate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getVisitorsScore(), visitorsTeamScore))
        );
    }

    @Test
    public void testGameCreatedHasVisitorsTeamScoreUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer visitorsTeamScore = 0;
        Game gameForCreate = Game.builder()
                .id(gameId)
                .visitorsScore(visitorsTeamScore)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        Game gameReturned = gameInMemoryRepository.create(gameForCreate);
        //then
        assertEquals(gameReturned.getVisitorsScore(), visitorsTeamScore);
    }

    @Test
    public void testGameForCreateHasStartTimeUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String startTime = "2020-07-10 15:00:00.000";
        Game gameForCreate = Game.builder()
                .id(gameId)
                .startTime(startTime)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        gameInMemoryRepository.create(gameForCreate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getStartTime(), startTime))
        );
    }

    @Test
    public void testGameCreatedHasStartTimeUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String startTime = "2020-07-10 15:00:00.000";
        Game gameForCreate = Game.builder()
                .id(gameId)
                .startTime(startTime)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForCreate);
        Game gameReturned = gameInMemoryRepository.create(gameForCreate);
        //then
        assertEquals(gameReturned.getStartTime(), startTime);
    }

    @Test
    public void testFindAllGamesCallsForAllValuesFromMap() {
        //when
        gameInMemoryRepository.findAllGames();
        //then
        verify(gameMap).values();
    }

    @Test
    public void testFindAllGamesCallsReturnsListOfValues() {
        Game firstGame = Game.builder()
                .id(UUID.randomUUID())
                .homeTeam("Germany")
                .visitors("Poland")
                .homeTeamScore(0)
                .visitorsScore(2)
                .startTime("2020-07-10 15:00:00.000")
                .build();
        Game secondGame = Game.builder()
                .id(UUID.randomUUID())
                .homeTeam("Netherlands")
                .visitors("England")
                .homeTeamScore(3)
                .visitorsScore(3)
                .startTime("2021-10-09 14:30:00.000")
                .build();
        List<Game> expectedList = List.of(firstGame, secondGame);
        //when
        when(gameMap.values()).thenReturn(expectedList);
        List<Game> gamesReturned = gameInMemoryRepository.findAllGames();
        //then
        assertTrue(areListsEqual(gamesReturned, expectedList));

    }

    @Test
    public void testGameUpdateCallsProperHashMapMethod() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeamScore(0)
                .visitorsScore(1)
                .build();
        //when
        gameInMemoryRepository.update(gameForUpdate);
        //then
        verify(gameMap).put(gameId, gameForUpdate);
    }

    @Test
    public void testGameForUpdateHasIdUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForUpdate);
        gameInMemoryRepository.update(gameForUpdate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getId(), gameId))
        );
    }

    @Test
    public void testGameForUpdateHasHomeTeamNameUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String homeTeam = "X";
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeam(homeTeam)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForUpdate);
        gameInMemoryRepository.update(gameForUpdate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getHomeTeam(), homeTeam))
        );
    }

    @Test
    public void testGameForUpdateHasVisitorsTeamNameUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String visitorsTeam = "Y";
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .visitors(visitorsTeam)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForUpdate);
        gameInMemoryRepository.update(gameForUpdate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getVisitors(), visitorsTeam))
        );
    }

    @Test
    public void testGameForUpdateHasHomeTeamScoreUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScore = 0;
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .homeTeamScore(homeTeamScore)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForUpdate);
        gameInMemoryRepository.update(gameForUpdate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getHomeTeamScore(), homeTeamScore))
        );
    }

    @Test
    public void testGameForUpdateHasVisitorsTeamScoreUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer visitorsScore = 1;
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .visitorsScore(visitorsScore)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForUpdate);
        gameInMemoryRepository.update(gameForUpdate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getVisitorsScore(), visitorsScore))
        );
    }

    @Test
    public void testGameForUpdateHasStartTimeUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String startTime = "2021-10-09 14:30:00.000";
        Game gameForUpdate = Game.builder()
                .id(gameId)
                .startTime(startTime)
                .build();
        //when
        when(gameMap.put(any(UUID.class), any(Game.class))).thenReturn(gameForUpdate);
        gameInMemoryRepository.update(gameForUpdate);
        //then
        verify(gameMap).put(
                argThat((UUID gameIdToVerify) -> Objects.equals(gameIdToVerify, gameId)),
                argThat((Game game) -> Objects.equals(game.getStartTime(), startTime))
        );
    }

    @Test
    public void testGameRemovalCallsProperHashMapMethod() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameForRemoval = Game.builder()
                .id(gameId)
                .build();
        //when
        gameInMemoryRepository.remove(gameForRemoval);
        //then
        verify(gameMap).remove(gameId);
    }

    @Test
    public void testFindGameByIdCallsProperHashMapMethod() {
        //given
        UUID gameId = UUID.randomUUID();
        //when
        gameInMemoryRepository.findGameById(gameId);
        //then
        verify(gameMap).get(gameId);
    }

    @Test
    public void testIfGameFoundHasIdUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Game gameExpected = Game.builder()
                .id(gameId)
                .build();
        //when
        when(gameMap.get(gameId)).thenReturn(gameExpected);
        Game gameReturned = gameInMemoryRepository.findGameById(gameId);
        //then
        assertEquals(gameReturned.getId(), gameExpected.getId());
    }

    @Test
    public void testIfGameFoundHasHomeTeamUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String homeTeamName = "X";
        Game gameExpected = Game.builder()
                .id(gameId)
                .homeTeam(homeTeamName)
                .build();
        //when
        when(gameMap.get(gameId)).thenReturn(gameExpected);
        Game gameReturned = gameInMemoryRepository.findGameById(gameId);
        //then
        assertEquals(gameReturned.getHomeTeam(), gameExpected.getHomeTeam());
    }

    @Test
    public void testIfGameFoundHasVisitorsTeamUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String visitorsTeamName = "Y";
        Game gameExpected = Game.builder()
                .id(gameId)
                .visitors(visitorsTeamName)
                .build();
        //when
        when(gameMap.get(gameId)).thenReturn(gameExpected);
        Game gameReturned = gameInMemoryRepository.findGameById(gameId);
        //then
        assertEquals(gameReturned.getVisitors(), gameExpected.getVisitors());
    }


    @Test
    public void testIfGameFoundHasHomeTeamScoreUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer homeTeamScore = 2;
        Game gameExpected = Game.builder()
                .id(gameId)
                .homeTeamScore(homeTeamScore)
                .build();
        //when
        when(gameMap.get(gameId)).thenReturn(gameExpected);
        Game gameReturned = gameInMemoryRepository.findGameById(gameId);
        //then
        assertEquals(gameReturned.getHomeTeamScore(), gameExpected.getHomeTeamScore());
    }

    @Test
    public void testIfGameFoundHasVisitorsTeamScoreUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        Integer visitorsTeamScore = 3;
        Game gameExpected = Game.builder()
                .id(gameId)
                .visitorsScore(visitorsTeamScore)
                .build();
        //when
        when(gameMap.get(gameId)).thenReturn(gameExpected);
        Game gameReturned = gameInMemoryRepository.findGameById(gameId);
        //then
        assertEquals(gameReturned.getVisitorsScore(), gameExpected.getVisitorsScore());
    }

    @Test
    public void testIfGameFoundHasStartTimeUnchanged() {
        //given
        UUID gameId = UUID.randomUUID();
        String startTime = "2021-10-09 14:30:00.000";
        Game gameExpected = Game.builder()
                .id(gameId)
                .startTime(startTime)
                .build();
        //when
        when(gameMap.get(gameId)).thenReturn(gameExpected);
        Game gameReturned = gameInMemoryRepository.findGameById(gameId);
        //then
        assertEquals(gameReturned.getStartTime(), gameExpected.getStartTime());
    }


    private boolean areListsEqual(List<Game> list1, List<Game> list2) {
        // If both lists are null, they are considered equal
        if (list1 == null && list2 == null) {
            return true;
        }
        // If one of the lists is null, they are not equal
        if (list1 == null || list2 == null) {
            return false;
        }
        // Check if the lists have the same size
        if (list1.size() != list2.size()) {
            return false;
        }
        // Check if the elements in the lists are equal
        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }

        return true;
    }

}