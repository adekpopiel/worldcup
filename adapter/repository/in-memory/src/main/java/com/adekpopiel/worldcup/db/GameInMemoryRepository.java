package com.adekpopiel.worldcup.db;

import com.adekpopiel.worldcup.domain.entity.Game;
import com.adekpopiel.worldcup.usecase.port.GameRepository;

import java.util.*;

public class GameInMemoryRepository implements GameRepository {

    private HashMap<UUID, Game> repository;
    @Override
    public Game create(Game game) {
        return repository.put(game.getId(), game);
    }

    @Override
    public List<Game> findAllGames() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public void update(Game game) {
        repository.put(game.getId(), game);
    }

    @Override
    public void remove(Game game) {
        repository.remove(game.getId());
    }

    @Override
    public Game findGameById(UUID id) {
        return repository.get(id);
    }
}
