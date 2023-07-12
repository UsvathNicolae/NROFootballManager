package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Result;
import com.nro.footballmanager.entity.dto.ResultDTO;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    List<Result> findAll();

    Optional<Result> getById(Long resultId);
    Result saveResult(Result result);



    Result updateResult(Long resultId, ResultDTO resultDTO);

    void deleteResultById(Long resultId);
}
