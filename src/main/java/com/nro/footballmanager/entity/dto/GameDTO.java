package com.nro.footballmanager.entity.dto;


import com.nro.footballmanager.entity.Game;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
public class GameDTO {
   private Long team1_id;

   private Long team2_id;

   private Long stadium_id;

   private LocalDateTime datetime;

   private Long result_id;

   public static GameDTO fromEntity(Game game) {
      GameDTO gameDTO = new GameDTO();

      gameDTO.setTeam1_id(game.getTeam1().getId());
      gameDTO.setTeam2_id(game.getTeam2().getId());
      gameDTO.setStadium_id(game.getStadium().getId());
      gameDTO.setDatetime(game.getDatetime());
      gameDTO.setResult_id(game.getResult().getId());

      return gameDTO;
   }

   public static Game toEntity(GameDTO gameDTO){
      Game game = new Game();

      game.getTeam1().setId(gameDTO.getTeam1_id());
      game.getTeam2().setId(gameDTO.getTeam2_id());
      game.getStadium().setId(gameDTO.getStadium_id());
      game.setDatetime(gameDTO.getDatetime());
      game.getResult().setId(gameDTO.getResult_id());

      return game;
   }
}