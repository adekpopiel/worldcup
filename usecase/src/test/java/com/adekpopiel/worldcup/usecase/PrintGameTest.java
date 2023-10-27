package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.port.GameRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrintGameTest {

    private static final String GAME_TEMPLATE = "%s %d - %s %d";

    @InjectMocks
    private PrintGame printGameUseCase;

    @Mock
    GameRepository gameRepository;

    @Mock
    PrintStream printStream;
    private static String printGameWithTotalScore10;
    private static String printGameWithTotalScore8;
    private static String printGameWithTotalScore8Earlier;
    private static String printGameWithTotalScore5;

    private static String printGameWithTotalScore10Later;
    private static List<Game> listOfGamesWithDifferentTotalScores;

    private static List<Game> listOfGamesWithSameTotalScoresButDifferentStartTime;

    private static List<Game> listOfAllGames;

    @BeforeAll()
    public static void initTest() {
        listOfGamesWithDifferentTotalScores = new ArrayList<>();
        listOfGamesWithSameTotalScoresButDifferentStartTime = new ArrayList<>();
        listOfAllGames = new ArrayList<>();
        Game gameWithTotalScore5 = Game.builder()
                .id(UUID.randomUUID())
                .homeTeam("Mexico")
                .visitors("Canada")
                .homeTeamScore(0)
                .visitorsScore(5)
                .startTime("2020-07-10 15:00:00.000")
                .build();
        Game gameWithTotalScore10 = Game.builder()
                .id(UUID.randomUUID())
                .homeTeam("Spain")
                .visitors("Brazil")
                .homeTeamScore(2)
                .visitorsScore(8)
                .startTime("2020-07-10 16:00:00.000")
                .build();
        Game gameWithTotalScore8 = Game.builder()
                .id(UUID.randomUUID())
                .homeTeam("Germany")
                .visitors("France")
                .homeTeamScore(4)
                .visitorsScore(4)
                .startTime("2020-07-10 15:30:00.000")
                .build();
        Game gameWithTotalScore8Earlier = Game.builder()
                .id(UUID.randomUUID())
                .homeTeam("Uruguay")
                .visitors("Italy")
                .homeTeamScore(4)
                .visitorsScore(4)
                .startTime("2020-07-10 12:30:00.000")
                .build();
        Game gameWithTotalScore10Later = Game.builder()
                .id(UUID.randomUUID())
                .homeTeam("P")
                .visitors("R")
                .homeTeamScore(5)
                .visitorsScore(5)
                .startTime("2020-07-10 17:00:00.000")
                .build();

        Collections.addAll(
                listOfGamesWithDifferentTotalScores,
                gameWithTotalScore5,
                gameWithTotalScore10,
                gameWithTotalScore8);
        Collections.addAll(listOfGamesWithDifferentTotalScores,
                gameWithTotalScore10,
                gameWithTotalScore10Later);
        Collections.addAll(listOfAllGames,
                gameWithTotalScore5,
                gameWithTotalScore8Earlier,
                gameWithTotalScore8,
                gameWithTotalScore10,
                gameWithTotalScore10Later);

        printGameWithTotalScore10 = String.format(GAME_TEMPLATE,
                gameWithTotalScore10.getHomeTeam(),
                gameWithTotalScore10.getHomeTeamScore(),
                gameWithTotalScore10.getVisitors(),
                gameWithTotalScore10.getVisitorsScore());
        printGameWithTotalScore8 = String.format(GAME_TEMPLATE,
                gameWithTotalScore8.getHomeTeam(),
                gameWithTotalScore8.getHomeTeamScore(),
                gameWithTotalScore8.getVisitors(),
                gameWithTotalScore8.getVisitorsScore());
        printGameWithTotalScore8Earlier = String.format(GAME_TEMPLATE,
                gameWithTotalScore8Earlier.getHomeTeam(),
                gameWithTotalScore8Earlier.getHomeTeamScore(),
                gameWithTotalScore8Earlier.getVisitors(),
                gameWithTotalScore8Earlier.getVisitorsScore());
        printGameWithTotalScore5 = String.format(GAME_TEMPLATE,
                gameWithTotalScore5.getHomeTeam(),
                gameWithTotalScore5.getHomeTeamScore(),
                gameWithTotalScore5.getVisitors(),
                gameWithTotalScore5.getVisitorsScore());
        printGameWithTotalScore10Later = String.format(GAME_TEMPLATE,
                gameWithTotalScore10Later.getHomeTeam(),
                gameWithTotalScore10Later.getHomeTeamScore(),
                gameWithTotalScore10Later.getVisitors(),
                gameWithTotalScore10Later.getVisitorsScore());
    }

    @Test
    public void testAreAllGamesFetchedFromTheRepository() {
        //when
        printGameUseCase.printScoreboard();
        //then
        verify(gameRepository).findAllGames();
    }

    @Test
    public void testPrintAllGamesDescendingToTheTotalScore() {
        //given
        String expectedScoreboard = printGameWithTotalScore10 + "\n" + printGameWithTotalScore8 + "\n" + printGameWithTotalScore5;
        //when
        when(gameRepository.findAllGames()).thenReturn(listOfGamesWithDifferentTotalScores);
        printGameUseCase.printScoreboard();
        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(printStream).println(captor.capture());

        assertEquals(expectedScoreboard, captor.getValue());
    }

    @Test
    public void testNoPrintEmptyScoreboardIfThereAreNoGamesInProgress() {
        //when
        when(gameRepository.findAllGames()).thenReturn(Collections.emptyList());
        printGameUseCase.printScoreboard();
        //then
        verify(printStream, never());
    }

    @Test
    public void testPrintGamesWithEqualTotalScoreOrderByTheRecentStartTime() {
        //given
        String expectedScoreboard = printGameWithTotalScore10Later + "\n" + printGameWithTotalScore10;
        //when
        when(gameRepository.findAllGames()).thenReturn(listOfGamesWithSameTotalScoresButDifferentStartTime);
        printGameUseCase.printScoreboard();
        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(printStream).println(captor.capture());

        assertEquals(expectedScoreboard, captor.capture());
    }

    @Test
    public void testPrintGamesWithDifferentTimesAndTotalScores() {
        //given
        String expectedScoreboard = printGameWithTotalScore10Later + "\n" + printGameWithTotalScore10
                + "\n" + printGameWithTotalScore8Earlier + "\n" + printGameWithTotalScore8
                + "\n" + printGameWithTotalScore5;
        //when
        when(gameRepository.findAllGames()).thenReturn(listOfAllGames);
        printGameUseCase.printScoreboard();
        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(printStream).println(captor.capture());

        assertEquals(expectedScoreboard, captor.capture());
    }

}
