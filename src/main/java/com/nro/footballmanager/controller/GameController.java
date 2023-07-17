package com.nro.footballmanager.controller;

import com.nro.footballmanager.entity.Game;
import com.nro.footballmanager.entity.dto.GameDTO;
import com.nro.footballmanager.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @PostMapping("/")
    public ResponseEntity<Game> saveGame(@Validated @RequestBody Game game) {
        return new ResponseEntity<>(gameService.saveGame(game), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Game>> fetchGamesList() {
        return new ResponseEntity<>(gameService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@RequestBody GameDTO gameDTO, @PathVariable("id") Long gameId){
        if (gameService.getById(gameId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(GameDTO.fromEntity(gameService.updateGame(gameId,gameDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGame(@PathVariable("id") Long gameId){
        if(gameService.getById(gameId).isPresent()){
            gameService.deleteGameById(gameId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getResult/{id}")
    public ResponseEntity<Game> generateResult(@PathVariable("id") Long gameId) {
        if (gameService.getById(gameId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameService.generateResult(gameId), HttpStatus.OK);
    }
}
