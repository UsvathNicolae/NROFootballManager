package com.nro.footballmanager.controller;
import com.nro.footballmanager.entity.Team;
import com.nro.footballmanager.service.TeamServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Team")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping("./")
    public Team saveTeam(@Validated @RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @GetMapping("./")
    public List<Team> fetchTeamsList() {
        return teamService.fetchTeamsList();
    }

    @PutMapping("./{id}")
    public Team updateTeam(@RequestBody Team team, @PathVariable("id") Long teamId){
        return teamService.updateTeam(team, teamId);
    }

    @DeleteMapping("./{id}")
    public String deleteTeam(@PathVariable("id") Long teamId){
        teamService.deleteTeam(teamId);
        return "Team deleted successfully";
    }
}
