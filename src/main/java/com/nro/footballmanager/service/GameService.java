package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Game;

import java.util.List;

public interface GameService {
    Game saveGame(Game game);

    List<Game> fetchGamesList();

    Game updateGame(Game game, Long gameId);

    void deleteGame(Long gameId);
}
