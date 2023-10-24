package com.adekpopiel.worldcup.usecase.port;

import com.adekpopiel.worldcup.domain.entity.Game;

import java.util.List;
import java.util.UUID;

public interface GameRepository {
    Game create(Game game);

    List<Game> findAllGames();

    void update(Game game);

    void remove(Game game);

    Game findGameById(UUID id);
}
