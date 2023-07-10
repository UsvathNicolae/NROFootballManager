package com.nro.footballmanager.controller;

import com.nro.footballmanager.entity.Stadium;
import com.nro.footballmanager.service.StadiumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stadium")
public class StadiumController {

    @Autowired
    private StadiumServiceImpl stadiumService;

    @PostMapping("./")
    public Stadium saveStadium(@Validated @RequestBody Stadium stadium) {
        return stadiumService.saveStadium(stadium);
    }

    @GetMapping("./")
    public List<Stadium> fetchStadiumsList() {
        return stadiumService.fetchStadiumsList();
    }

    @PutMapping("./{id}")
    public Stadium updateStadium(@RequestBody Stadium stadium, @PathVariable("id") Long stadiumId){
        return stadiumService.updateStadium(stadium, stadiumId);
    }

    @DeleteMapping("./{id}")
    public String deleteStadium(@PathVariable("id") Long stadiumId){
        stadiumService.deleteStadium(stadiumId);
        return "Stadium deleted successfully";
    }
}