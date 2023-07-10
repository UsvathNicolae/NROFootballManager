package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Team;

import java.util.List;

public interface TeamService {
    Team saveTeam(Team team);

    List<Team> fetchTeamsList();

    Team updateTeam(Team team, Long teamId);

    void deleteTeam(Long teamId);
}
