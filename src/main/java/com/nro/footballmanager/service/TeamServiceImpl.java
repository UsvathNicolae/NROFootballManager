package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Team;
import com.nro.footballmanager.repository.PlayerRepository;
import com.nro.footballmanager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;
    @Override
    public Team saveTeam(Team team){
        return teamRepository.save(team);
    }

    @Override
    public List<Team> fetchTeamsList(){
        return teamRepository.findAll();
    }

    @Override
    public Team updateTeam(Team team, Long id){
        Team teamDB = teamRepository.findById(id).get();

        if(team.getName() != null){
            teamDB.setName(team.getName());
        }

        if(team.getGoalsScored() >= 0){
            teamDB.setGoalsScored(team.getGoalsScored());
        }

        if(team.getGoalsReceived() >= 0){
            teamDB.setGoalsReceived(team.getGoalsReceived());
        }

        if(team.getVictories() >= 0){
            teamDB.setVictories(team.getVictories());
        }

        if(team.getDefeats() >= 0){
            teamDB.setDefeats(team.getDefeats());
        }

        if(team.getDraws() >= 0){
            teamDB.setDraws(team.getDraws());
        }
        return teamRepository.save(teamDB);
    }

    @Override
    public void deleteTeam(Long id){
        teamRepository.deleteById(id);
    }
}
