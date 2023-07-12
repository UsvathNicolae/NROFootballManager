package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Game;
import com.nro.footballmanager.entity.dto.GameDTO;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Game saveGame(Game game);

    Optional<Game> getById(Long gameId);

    List<Game> findAll();

    Game updateGame(Long gameId, GameDTO gameDTO);

    void deleteGameById(Long gameId);
}
