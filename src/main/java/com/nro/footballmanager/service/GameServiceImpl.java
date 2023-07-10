package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Game;
import com.nro.footballmanager.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game saveGame(Game game){
        return gameRepository.save(game);
    }

    @Override
    public List<Game> fetchGamesList(){
        return gameRepository.findAll();
    }

    @Override
    public Game updateGame(Game game, Long gameId){

        Game gameDB = gameRepository.findById(gameId).get();

        if(game.getTeam1() != null){
            gameDB.setTeam1(game.getTeam1());
        }

        if(game.getTeam2() != null){
            gameDB.setTeam2(game.getTeam2());
        }

        if(game.getResult() != null){
            gameDB.setResult(game.getResult());
        }

        if(game.getStadium() != null){
            gameDB.setStadium(game.getStadium());
        }

        if(game.getStartHour() != null){
            gameDB.setStartHour(game.getStartHour());
        }

        if(game.getDate() != null){
            gameDB.setDate(game.getDate());
        }

        return gameRepository.save(gameDB);
    }

    @Override
    public void deleteGame(Long gameId){
        gameRepository.deleteById(gameId);
    }
}
