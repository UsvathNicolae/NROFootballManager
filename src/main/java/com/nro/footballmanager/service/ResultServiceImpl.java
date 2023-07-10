package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Result;
import com.nro.footballmanager.entity.Stadium;
import com.nro.footballmanager.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService{
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result saveResult(Result result){
        return resultRepository.save(result);
    }

    @Override
    public List<Result> fetchResultsList(){
        return resultRepository.findAll();
    }

    @Override
    public Result updateResult(Result result, Long resultId){
        Result resultDB = resultRepository.findById(resultId).get();

        if(result.getGoalsTeamOne() >= 0){
            resultDB.setGoalsTeamOne(result.getGoalsTeamOne());
        }

        if(result.getGoalsTeamTwo() >= 0){
            resultDB.setGoalsTeamTwo(result.getGoalsTeamTwo());
        }

        if(result.getGame() != null ){
            resultDB.setGame(result.getGame());
        }

        return resultRepository.save(resultDB);
    }

    @Override
    public void deleteResult(Long resultId){
        resultRepository.deleteById(resultId);
    }
}
