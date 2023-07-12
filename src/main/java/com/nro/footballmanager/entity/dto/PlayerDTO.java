package com.nro.footballmanager.entity.dto;

import com.nro.footballmanager.entity.Player;
import com.nro.footballmanager.entity.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    private String name;
    private int goalsScored;
    private RoleEnum role;
    private Long teamId;

    public static PlayerDTO fromEntity(Player player){
        PlayerDTO playerDTO = new PlayerDTO();

        playerDTO.setName(player.getName());
        playerDTO.setGoalsScored(player.getGoalsScored());
        playerDTO.setRole(player.getRole());
        playerDTO.setTeamId(player.getTeam().getId());

        return playerDTO;
    }

    public static Player toEntity(PlayerDTO playerDTO){
        Player player = new Player();

        player.setName(playerDTO.getName());
        player.setGoalsScored(playerDTO.getGoalsScored());
        player.setRole(playerDTO.getRole());
        player.getTeam().setId(playerDTO.getTeamId());

        return player;
    }
}