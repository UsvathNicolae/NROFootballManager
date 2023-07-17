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
        return playerRepository.findAll();
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
}
