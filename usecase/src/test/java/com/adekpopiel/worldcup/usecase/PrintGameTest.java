package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.port.DateFormatter;
import com.adekpopiel.worldcup.usecase.port.GameRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
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
    private GameRepository gameRepository;

    @Mock
    private DateFormatter dateFormatter;

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
                .homeTeam("Argentina")
                .visitors("Australia")
                .homeTeamScore(5)
                .visitorsScore(5)
                .startTime("2020-07-10 17:00:00.000")
                .build();

        Collections.addAll(
                listOfGamesWithDifferentTotalScores,
                gameWithTotalScore5,
                gameWithTotalScore10,
                gameWithTotalScore8);
        Collections.addAll(listOfGamesWithSameTotalScoresButDifferentStartTime,
                gameWithTotalScore10,
                gameWithTotalScore10Later);
        Collections.addAll(listOfAllGames,
                gameWithTotalScore5,
                gameWithTotalScore8Earlier,
                gameWithTotalScore8,
                gameWithTotalScore10,
                gameWithTotalScore10Later);

        printGameWithTotalScore10 = gameWithTotalScore10.toString();
        printGameWithTotalScore8 = gameWithTotalScore8.toString();
        printGameWithTotalScore8Earlier = gameWithTotalScore8Earlier.toString();
        printGameWithTotalScore5 = gameWithTotalScore5.toString();
        printGameWithTotalScore10Later = gameWithTotalScore10Later.toString();
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
        String expectedScoreboard = printGameWithTotalScore10 + "\n" + printGameWithTotalScore8 + "\n" + printGameWithTotalScore5 + "\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printGameUseCase.setPrintStream(printStream);
        //when
        when(gameRepository.findAllGames()).thenReturn(listOfGamesWithDifferentTotalScores);
        printGameUseCase.printScoreboard();
        //then
       assertEquals(expectedScoreboard, outputStream.toString());
    }

    @Test
    public void testNoPrintEmptyScoreboardIfThereAreNoGamesInProgress() {
        //given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printGameUseCase.setPrintStream(printStream);
        //when
        when(gameRepository.findAllGames()).thenReturn(Collections.emptyList());
        printGameUseCase.printScoreboard();
        //then
        assertEquals("",outputStream.toString());
    }

    @Test
    public void testPrintGamesWithEqualTotalScoreOrderByTheRecentStartTime() {
        //given
        String expectedScoreboard = printGameWithTotalScore10Later + "\n" + printGameWithTotalScore10 + "\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printGameUseCase.setPrintStream(printStream);
        //when
        when(dateFormatter.getFormatterString()).thenReturn("yyyy-MM-dd HH:mm:ss.SSS");
        when(gameRepository.findAllGames()).thenReturn(listOfGamesWithSameTotalScoresButDifferentStartTime);
        printGameUseCase.printScoreboard();
        //then
        assertEquals(expectedScoreboard, outputStream.toString());
    }

    @Test
    public void testPrintGamesWithDifferentTimesAndTotalScores() {
        //given
        String expectedScoreboard = printGameWithTotalScore10Later + "\n" + printGameWithTotalScore10
                + "\n" + printGameWithTotalScore8 + "\n" + printGameWithTotalScore8Earlier
                + "\n" + printGameWithTotalScore5 + "\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printGameUseCase.setPrintStream(printStream);
        //when
        when(dateFormatter.getFormatterString()).thenReturn("yyyy-MM-dd HH:mm:ss.SSS");
        when(gameRepository.findAllGames()).thenReturn(listOfAllGames);
        printGameUseCase.printScoreboard();
        //then
        assertEquals(expectedScoreboard, outputStream.toString());
    }

}
