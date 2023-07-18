package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Player;
import com.nro.footballmanager.entity.dto.PlayerDTO;
import com.nro.footballmanager.repository.PlayerRepository;
import com.nro.footballmanager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamServiceImpl teamService;

    @Override
    public Player savePlayer(Player player){
        return playerRepository.save(player);
    }

    @Override
    public List<Player> findAll(){
        List<Player> players = playerRepository.findAll();
        players.sort((player1, player2) ->  player2.getGoalsScored() - player1.getGoalsScored());
        return players;
    }

    @Override
    public List<Player> findTopNPlayers(int number){
        List<Player> players = findAll();
        return players.stream().limit(number).collect(Collectors.toList());
    }

    @Override
    public Player updatePlayer(Long playerId, PlayerDTO playerDTO){
        Player player = PlayerDTO.toEntity(playerDTO);
        player.setId(playerId);
        player.setTeam(teamService.getById(playerDTO.getTeamId()).get());
        return playerRepository.save(player);
    }

    @Override
    public Optional<Player> getById(Long playerId){
        return playerRepository.findById(playerId);
    }

    @Override
    public void deletePlayerById(Long playerId){
        playerRepository.deleteById(playerId);
    }

    @Override
    public List<Player> findAllByTeamId(Long teamId){
        return playerRepository.findAllByTeamId(teamId);
    }
}
