package com.adekpopiel.worldcup.usecase;

import com.adekpopiel.worldcup.usecase.port.GameRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PrintGameTest {

    @InjectMocks
    private PrintGame printGameUseCase;

    @Mock
    GameRepository gameRepository;
}
