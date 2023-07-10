package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Player;
import com.nro.footballmanager.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player savePlayer(Player player){
        return playerRepository.save(player);
    }

    @Override
    public List<Player> fetchPlayersList(){
        return (List<Player>) playerRepository.findAll();
    }

    @Override
    public Player updatePlayer(Player player, Long playerId){
        Player playerDB = playerRepository.findById(playerId).get();

        if(Objects.nonNull(player.getName()) && !"".equalsIgnoreCase(player.getName())) {
            playerDB.setName(player.getName());
        }

        if(player.getGoalsScored() >= 0) {
            playerDB.setGoalsScored(player.getGoalsScored());
        }

        if(Objects.nonNull(player.getRole())) {
            playerDB.setRole(player.getRole());
        }

        if(Objects.nonNull(player.getTeam())) {
            playerDB.setTeam(player.getTeam());
        }

        return playerRepository.save(playerDB);
    }

    @Override
    public void deletePlayerById(Long playerId){
        playerRepository.deleteById(playerId);
    }
}
