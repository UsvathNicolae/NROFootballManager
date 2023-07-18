package com.nro.footballmanager.controller;
import com.nro.footballmanager.entity.Team;
import com.nro.footballmanager.entity.dto.TeamDTO;
import com.nro.footballmanager.service.TeamServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;



    @PostMapping("/")
    public ResponseEntity<Team> saveTeam(@Validated @RequestBody Team team) {
        return new ResponseEntity<>(teamService.saveTeam(team),HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Team>> fetchTeamsList() {
        return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/top/{number}")
    public ResponseEntity<List<Team>> findTopTeams(@PathVariable("number") int number) {
        return new ResponseEntity<>(teamService.findTopNTeams(number), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@RequestBody TeamDTO teamDTO, @PathVariable("id") Long teamId) {
        if (teamService.getById(teamId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(TeamDTO.fromEntity(teamService.updateTeam(teamId, teamDTO)), HttpStatus.OK);
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") Long teamId){
            if(teamService.getById(teamId).isPresent()){
                teamService.deleteTeamById(teamId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
