package com.nro.footballmanager.entity.dto;

import com.nro.footballmanager.entity.Team;
import com.nro.footballmanager.entity.Player;
import com.nro.footballmanager.entity.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Setter
@Getter
public class TeamDTO {
    private String name;

    private int goalsScored;

    private int goalsReceived;

    private int victories;

    private int defeats;

    private int draws;

    public static TeamDTO fromEntity(Team team) {
        TeamDTO teamDTO = new TeamDTO();

        teamDTO.setName(team.getName());
        teamDTO.setGoalsScored(team.getGoalsScored());
        teamDTO.setGoalsReceived(team.getGoalsReceived());
        teamDTO.setVictories(team.getVictories());
        teamDTO.setDefeats(team.getDefeats());
        teamDTO.setDraws(team.getDraws());

//        teamDTO.setPlayers_ids(team.getPlayers()
//                .stream()
//                .map(Player::getId)
//                .collect(Collectors.toList()));
//
//        teamDTO.setGamesPlayedAsTeamOne(team.getGamesPlayedAsTeamOne()
//                .stream()
//                .map(Game::getId)
//                .collect(Collectors.toList()));
//
//        teamDTO.setGamesPlayedAsTeamTwo(team.getGamesPlayedAsTeamTwo()
//                .stream().map(Game::getId)
//                .collect(Collectors.toList()));

        return teamDTO;
    }

    public static Team toEntity(TeamDTO teamDTO) {
        Team team = new Team();

        team.setName(teamDTO.getName());
        team.setGoalsScored(teamDTO.getGoalsScored());
        team.setGoalsReceived(teamDTO.getGoalsReceived());
        team.setVictories(teamDTO.getVictories());
        team.setDefeats(teamDTO.getDefeats());
        team.setDraws(teamDTO.getDraws());

//        IntStream.range(0, teamDTO.getPlayers_ids().size())
//                .forEach(i -> team.getPlayers().get(i).setId(teamDTO.getPlayers_ids().get(i)));
//
//        IntStream.range(0, teamDTO.getGamesPlayedAsTeamOne().size())
//                .forEach(i -> team.getGamesPlayedAsTeamOne().get(i).setId(teamDTO.gamesPlayedAsTeamOne.get(i)));
//
//        IntStream.range(0, teamDTO.getGamesPlayedAsTeamTwo().size())
//                .forEach(i -> team.getGamesPlayedAsTeamTwo().get(i).setId(teamDTO.getGamesPlayedAsTeamTwo().get(i)));

        return team;
    }
}
