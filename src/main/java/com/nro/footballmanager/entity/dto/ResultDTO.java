package com.nro.footballmanager.entity.dto;

import com.nro.footballmanager.entity.Result;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDTO{
    private int goalsTeamOne;

    private int goalsTeamTwo;

    private Long game_id;

    public static ResultDTO fromEntity(Result result){
        ResultDTO resultDTO = new ResultDTO();

        resultDTO.setGoalsTeamOne(result.getGoalsTeamOne());
        resultDTO.setGoalsTeamTwo(result.getGoalsTeamTwo());

        resultDTO.setGame_id(result.getGame().getId());

        return resultDTO;
    }

    public static Result toEntity(ResultDTO resultDTO){
        Result result = new Result();

        result.setGoalsTeamOne(resultDTO.getGoalsTeamOne());
        result.setGoalsTeamTwo(resultDTO.getGoalsTeamTwo());
        result.getGame().setId(resultDTO.getGame_id());

        return result;
    }
}
