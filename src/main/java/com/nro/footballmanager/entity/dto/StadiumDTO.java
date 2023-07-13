package com.nro.footballmanager.entity.dto;

import com.nro.footballmanager.entity.Game;
import com.nro.footballmanager.entity.Result;
import com.nro.footballmanager.entity.Stadium;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class StadiumDTO {
    private String name;

    private String location;

    private List<Long> gamesOnStadium;

    public static StadiumDTO fromEntity(Stadium stadium){
        StadiumDTO stadiumDTO = new StadiumDTO();

        stadiumDTO.setName(stadium.getName());
        stadiumDTO.setLocation(stadium.getLocation());

//        List<Long> gamesOnStadiumList = stadium.getGamesOnStadium()
//                .stream()
//                .map(Game::getId)
//                .collect(Collectors.toList());
//        stadiumDTO.setGamesOnStadium(gamesOnStadiumList);

        return stadiumDTO;
    }

    public static Stadium toEntity(StadiumDTO stadiumDTO){
        Stadium stadium = new Stadium();

        stadium.setName(stadiumDTO.getName());
        stadium.setLocation(stadiumDTO.getLocation());

//        IntStream.range(0, stadium.getGamesOnStadium().size())
//                .forEach(i -> stadium.getGamesOnStadium().get(i).setId(stadiumDTO.getGamesOnStadium().get(i)));

        return stadium;
    }
}
