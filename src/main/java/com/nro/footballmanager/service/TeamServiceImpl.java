package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Team;
import com.nro.footballmanager.entity.dto.TeamDTO;
import com.nro.footballmanager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return teamRepository.findAll();
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
