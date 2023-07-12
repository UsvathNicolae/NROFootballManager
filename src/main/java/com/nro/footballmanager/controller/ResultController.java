package com.nro.footballmanager.controller;

import com.nro.footballmanager.entity.Result;
import com.nro.footballmanager.entity.dto.ResultDTO;
import com.nro.footballmanager.service.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultServiceImpl resultService;

    @PostMapping("/")
    public ResponseEntity<Result> saveResult(@Validated @RequestBody Result result) {
        return new ResponseEntity<>(resultService.saveResult(result),HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Result>> fetchResultsList() {
        return new ResponseEntity<>(resultService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> updateResult(@RequestBody ResultDTO resultDTO, @PathVariable("id") Long resultId){
        if(resultService.getById(resultId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ResultDTO.fromEntity(resultService.updateResult(resultId, resultDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteResult(@PathVariable("id") Long resultId){
        if(resultService.getById(resultId).isPresent()){
            resultService.deleteResultById(resultId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
