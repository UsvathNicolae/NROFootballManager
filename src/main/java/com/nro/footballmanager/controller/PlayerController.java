package com.nro.footballmanager.controller;

import com.nro.footballmanager.entity.Player;
import com.nro.footballmanager.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerServiceImpl playerService;

    @PostMapping("./")
    public Player savePlayer(@Validated @RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @GetMapping("./")
    public List<Player> fetchPlayersList() {
        return playerService.fetchPlayersList();
    }

    @PutMapping("./{id}")
    public Player updatePlayer(@RequestBody Player player, @PathVariable("id") Long playerId){
        return playerService.updatePlayer(player, playerId);
    }

    @DeleteMapping("./{id}")
    public String deletePlayer(@PathVariable("id") Long playerId){
        playerService.deletePlayerById(playerId);
        return "Player deleted successfully";
    }
}
