package com.adekpopiel.worldcup.usecase.validation;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameAlreadyExistException;
import com.adekpopiel.worldcup.usecase.exception.GameValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameValidatorTest {

    public static final String GAME_CANNOT_BE_NULL = "Game cannot be null";
    public static final String HOME_TEAM_NAME_HAS_TO_BE_SET = "Home team name has to be set";
    public static final String VISITORS_TEAM_NAME_HAS_TO_BE_SET = "Visitors team name has to be set";

    @Test
    public void testGameIsNotNull() {
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> GameValidator.validate(null));
        assertTrue(exception.getMessage().contains(GAME_CANNOT_BE_NULL));

    }

    @Test
    public void testGameHomeTeamIsNotNull() {
        Game game = Game.builder()
                .visitors("Y")
                .build();
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> GameValidator.validate(game));
        assertTrue(exception.getMessage().contains(HOME_TEAM_NAME_HAS_TO_BE_SET));

    }

    @Test
    public void testGameHomeTeamIsNotEmpty() {
        Game game = Game.builder()
                .homeTeam("")
                .visitors("Y")
                .build();
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> GameValidator.validate(game));
        assertTrue(exception.getMessage().contains(HOME_TEAM_NAME_HAS_TO_BE_SET));

    }

    @Test
    public void testGameVisitorsIsNotNull() {
        Game game = Game.builder()
                .homeTeam("Y")
                .build();
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> GameValidator.validate(game));
        assertTrue(exception.getMessage().contains(VISITORS_TEAM_NAME_HAS_TO_BE_SET));

    }

    @Test
    public void testGameVisitorsIsNotEmpty() {
        Game game = Game.builder()
                .homeTeam("X")
                .visitors("")
                .build();
        GameValidationException exception = assertThrows(
                GameValidationException.class, () -> GameValidator.validate(game));
        assertTrue(exception.getMessage().contains(VISITORS_TEAM_NAME_HAS_TO_BE_SET));

    }
}