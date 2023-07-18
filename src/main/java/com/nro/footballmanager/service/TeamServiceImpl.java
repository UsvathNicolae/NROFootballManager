package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Player;
import com.nro.footballmanager.entity.Team;
import com.nro.footballmanager.entity.dto.TeamDTO;
import com.nro.footballmanager.repository.PlayerRepository;
import com.nro.footballmanager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team saveTeam(Team team){
        return teamRepository.save(team);
    }

    @Override
    public Optional<Team> getById(Long teamId){
        return teamRepository.findById(teamId);
    }

    @Override
    public List<Team> findAll(){
        List<Team> teams = teamRepository.findAll();
        teams.sort((team1, team2) -> {
             int comparation =((team2.getVictories() * 3 + team2.getDraws()) - (team1.getVictories() * 3 + team1.getDraws()));
             if(comparation == 0){
                 comparation = team2.getGoalsScored() - team1.getGoalsScored();
             }
            if(comparation == 0){
                comparation = team1.getGoalsReceived() - team2.getGoalsReceived();
            }
             return comparation;
        });
        return teams;
    }

    @Override
    public List<Team> findTopNTeams(int number){
        List<Team> teams = findAll();
        return teams.stream().limit(number).collect(Collectors.toList());
    }

    @Override
    public Team updateTeam(Long teamId, TeamDTO teamDTO){
        Team team = TeamDTO.toEntity(teamDTO);
        team.setId(teamId);
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeamById(Long id){
        teamRepository.deleteById(id);
    }
}
