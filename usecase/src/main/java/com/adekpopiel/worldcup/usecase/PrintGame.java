package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.port.DateFormatter;
import com.adekpopiel.worldcup.usecase.port.GameRepository;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PrintGame {

    private GameRepository gameRepository;
    private PrintStream printStream;
    private DateFormatter dateFormatter;


    public void printScoreboard() {
        List<Game> listOfAllGames = gameRepository.findAllGames();
        listOfAllGames.stream()
                .sorted(byTotalScoreAndStartTime())
                .forEach(printGame());
    }

    private Consumer<Game> printGame() {
        Supplier<Consumer<Game>> consumerSupplier = () -> game -> {
            printStream.println(game);
        };
        return consumerSupplier.get();
    }

    private Comparator<Game> byTotalScoreAndStartTime() {
        Supplier<Comparator<Game>> comparatorSupplier = () -> (game1, game2) -> {
            int totalScoreComparison = Integer.compare(game2.getTotalScore(), game1.getTotalScore());
            if (totalScoreComparison == 0) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatter.getFormatterString());
                LocalDateTime date1 = LocalDateTime.parse(
                        game1.getStartTime(),
                        formatter);
                LocalDateTime date2 = LocalDateTime.parse(
                        game2.getStartTime(),
                        formatter);
                return date2.compareTo(date1);
            }
            return totalScoreComparison;
        };
        return comparatorSupplier.get();
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void setDateFormatter(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }
}
