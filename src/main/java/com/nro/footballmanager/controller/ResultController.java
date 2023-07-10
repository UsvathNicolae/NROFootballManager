package com.nro.footballmanager.controller;

import com.nro.footballmanager.entity.Result;
import com.nro.footballmanager.service.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultServiceImpl resultService;

    @PostMapping("./")
    public Result saveResult(@Validated @RequestBody Result result) {
        return resultService.saveResult(result);
    }

    @GetMapping("./")
    public List<Result> fetchResultsList() {
        return resultService.fetchResultsList();
    }

    @PutMapping("./{id}")
    public Result updateResult(@RequestBody Result result, @PathVariable("id") Long resultId){
        return resultService.updateResult(result, resultId);
    }

    @DeleteMapping("./{id}")
    public String deleteResult(@PathVariable("id") Long resultId){
        resultService.deleteResult(resultId);
        return "Result deleted successfully";
    }
}
