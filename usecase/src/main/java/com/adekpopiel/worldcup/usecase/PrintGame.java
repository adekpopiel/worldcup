package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.usecase.port.GameRepository;

import java.io.PrintStream;

public class PrintGame {

    private GameRepository gameRepository;

    private PrintStream printStream;

    public void printScoreboard() {

    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
