package com.adekpopiel.worldcup.usecase.exception;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String message) {
        super(message);
    }
}
