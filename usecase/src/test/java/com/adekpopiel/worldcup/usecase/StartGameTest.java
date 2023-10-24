package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.exception.GameAlreadyExistException;
import com.adekpopiel.worldcup.usecase.port.DateFormatter;
import com.adekpopiel.worldcup.usecase.port.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartGameTest {

    public static final String GAME_NOT_FOUND_MESSAGE = "Game was not found!";
    @InjectMocks
    private StartGame startGameUseCase;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private DateFormatter dateFormatter;

    @Test
    public void testNewGameIsStarted() {
        //given
        Game newGame = Game.builder()
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        startGameUseCase.startGame(newGame);
        //then
        verify(gameRepository).create(newGame);
    }

    @Test
    public void testReturnExceptionIfGameAlreadyExists() {
        //given
        UUID gameId = UUID.randomUUID();
        Game newGame = Game.builder()
                .id(gameId)
                .homeTeam("X")
                .visitors("Y")
                .build();
        //when
        when(gameRepository.findGameById(gameId)).thenReturn(newGame);
        GameAlreadyExistException exception = assertThrows(GameAlreadyExistException.class, () -> startGameUseCase.startGame(newGame));
        //then
        assertTrue(exception.getMessage().contains(GAME_NOT_FOUND_MESSAGE));
    }



}