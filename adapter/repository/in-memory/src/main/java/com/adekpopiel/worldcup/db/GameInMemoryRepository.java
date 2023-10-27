package com.adekpopiel.worldcup.db;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.port.GameRepository;

import java.util.List;
import java.util.UUID;

public class GameInMemoryRepository implements GameRepository {
    @Override
    public Game create(Game game) {
        return null;
    }

    @Override
    public List<Game> findAllGames() {
        return null;
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void remove(Game game) {

    }

    @Override
    public Game findGameById(UUID id) {
        return null;
    }
}
