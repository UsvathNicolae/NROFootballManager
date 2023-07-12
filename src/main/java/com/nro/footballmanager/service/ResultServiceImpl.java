package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Result;
import com.nro.footballmanager.entity.dto.ResultDTO;
import com.nro.footballmanager.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService{
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result saveResult(Result result){
        return resultRepository.save(result);
    }

    @Override
    public Optional<Result> getById(Long resultId){
        return resultRepository.findById(resultId);
    }

    @Override
    public List<Result> findAll(){
        return resultRepository.findAll();
    }

    @Override
    public Result updateResult(Long resultId, ResultDTO resultDTO ){
       Result result = ResultDTO.toEntity(resultDTO);
       result.setId(resultId);
       return resultRepository.save(result);
    }

    @Override
    public void deleteResultById(Long resultId){
        resultRepository.deleteById(resultId);
    }
}
