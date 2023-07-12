package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Team;
import com.nro.footballmanager.entity.dto.TeamDTO;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    List<Team> findAll();

    Optional<Team> getById(Long teamId);
    Team saveTeam(Team team);

    Team updateTeam(Long teamId, TeamDTO teamDTO);

    void deleteTeamById(Long teamId);
}
