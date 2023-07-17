package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Game;
import com.nro.footballmanager.entity.Player;
import com.nro.footballmanager.entity.Result;
import com.nro.footballmanager.entity.Team;
import com.nro.footballmanager.entity.dto.GameDTO;
import com.nro.footballmanager.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamServiceImpl teamService;

    @Autowired
    private ResultServiceImpl resultService;

    @Autowired
    private PlayerServiceImpl playerService;

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

    @Override
    public Game generateResult(Long gameId){
        Game game = gameRepository.findById(gameId).get();
        if(game.getResult() == null){
            Result result = new Result();
            Random random = new Random();
            result.setGoalsTeamOne(random.nextInt(6) );
            result.setGoalsTeamTwo(random.nextInt(6) );

            Team team1 = teamService.getById(game.getTeam1().getId()).get();
            team1.setGoalsScored(team1.getGoalsScored() + result.getGoalsTeamOne());
            team1.setGoalsReceived(team1.getGoalsReceived() + result.getGoalsTeamTwo());

            Team team2 = teamService.getById(game.getTeam2().getId()).get();
            team2.setGoalsScored(team2.getGoalsScored() + result.getGoalsTeamTwo());
            team2.setGoalsReceived(team2.getGoalsReceived() + result.getGoalsTeamOne());

            List<Player> players = playerService.findAllByTeamId(team1.getId());
            if(players.size()>0){
                int i = 0;
                while(i < result.getGoalsTeamOne()){
                    int playerWhoScored = random.nextInt(players.size());
                    players.get(playerWhoScored).setGoalsScored(players.get(playerWhoScored).getGoalsScored() + 1);
                    playerService.savePlayer(players.get(playerWhoScored));
                    i++;
                }
            }

            players = playerService.findAllByTeamId(team2.getId());
            if(players.size()>0){
                int i=0;
                while(i < result.getGoalsTeamTwo()){
                    int playerWhoScored = random.nextInt(players.size());
                    players.get(playerWhoScored).setGoalsScored(players.get(playerWhoScored).getGoalsScored() + 1);
                    playerService.savePlayer(players.get(playerWhoScored));
                    i++;
                }
            }

            if (result.getGoalsTeamOne() > result.getGoalsTeamTwo()){
                team1.setVictories(team1.getVictories() + 1);
                team2.setDefeats(team2.getVictories() + 1);
            }else if(result.getGoalsTeamOne() < result.getGoalsTeamTwo()){
                team2.setVictories(team2.getVictories() + 1);
                team1.setDefeats(team1.getVictories() + 1);
            }else {
                team1.setDraws(team1.getDraws() + 1);
                team2.setDraws(team2.getDraws() + 1);
            }

            resultService.saveResult(result);
            teamService.saveTeam(team1);
            teamService.saveTeam(team2);
            game.setResult(result);
            gameRepository.save(game);
        }
        return game;
    }
}
