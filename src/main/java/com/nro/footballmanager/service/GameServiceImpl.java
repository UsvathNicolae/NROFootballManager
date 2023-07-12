package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Game;
import com.nro.footballmanager.entity.dto.GameDTO;
import com.nro.footballmanager.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game saveGame(Game game){
        return gameRepository.save(game);
    }

    @Override
    public Optional<Game> getById(Long gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public List<Game> findAll(){
        return gameRepository.findAll();
    }

    @Override
    public Game updateGame(Long gameId, GameDTO gameDTO){
        Game game = GameDTO.toEntity(gameDTO);
        game.setId(gameId);
        return gameRepository.save(game);
    }

    @Override
    public void deleteGameById(Long gameId){
        gameRepository.deleteById(gameId);
    }
}
