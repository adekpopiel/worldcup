package com.adekpopiel.worldcup.usecase.exception;

public class GameValidationException extends RuntimeException {
    public GameValidationException(String message) {
        super(message);
    }
}
