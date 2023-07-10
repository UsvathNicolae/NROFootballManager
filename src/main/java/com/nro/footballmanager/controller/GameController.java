package com.nro.footballmanager.controller;

import com.nro.footballmanager.entity.Game;
import com.nro.footballmanager.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @PostMapping("./")
    public Game saveGame(@Validated @RequestBody Game game) {
        return gameService.saveGame(game);
    }

    @GetMapping("./")
    public List<Game> fetchGamesList() {
        return gameService.fetchGamesList();
    }

    @PutMapping("./{id}")
    public Game updateGame(@RequestBody Game game, @PathVariable("id") Long gameId){
        return gameService.updateGame(game, gameId);
    }

    @DeleteMapping("./{id}")
    public String deleteGame(@PathVariable("id") Long gameId){
        gameService.deleteGame(gameId);
        return "Game deleted successfully";
    }
}
