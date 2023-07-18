package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Player;
import com.nro.footballmanager.entity.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    List<Player> findAll();

    List<Player> findTopNPlayers(int number);

    Optional<Player> getById(Long playerId);

    Player updatePlayer(Long playerId, PlayerDTO playerDTO);

    Player savePlayer(Player player);

    void deletePlayerById(Long playerId);

    List<Player> findAllByTeamId(Long teamId);
}
