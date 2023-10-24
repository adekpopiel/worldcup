package com.adekpopiel.worldcup.usecase.exception;

public class GameAlreadyExistException extends RuntimeException {
    public GameAlreadyExistException(String message) {
        super(message);
    }
}
