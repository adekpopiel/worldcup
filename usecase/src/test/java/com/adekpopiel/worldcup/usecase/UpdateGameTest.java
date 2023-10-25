package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.usecase.port.GameRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateGameTest {

    @InjectMocks
    UpdateGame updateGameUseCase;

    @Mock
    GameRepository gameRepository;
}