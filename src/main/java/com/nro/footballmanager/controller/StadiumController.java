package com.nro.footballmanager.controller;

import com.nro.footballmanager.entity.Stadium;
import com.nro.footballmanager.entity.dto.StadiumDTO;
import com.nro.footballmanager.service.StadiumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stadiums")
public class StadiumController {

    @Autowired
    private StadiumServiceImpl stadiumService;

    @GetMapping("/")
    public ResponseEntity<List<Stadium>> getAllStadiums() {
        return new ResponseEntity<>(stadiumService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Stadium> saveStadium(@Validated @RequestBody Stadium stadium) {
        return new ResponseEntity<>(stadiumService.saveStadium(stadium), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<StadiumDTO> updateStadium(@RequestBody StadiumDTO stadiumDTO, @PathVariable("id") Long stadiumId){
        if (stadiumService.getById(stadiumId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(StadiumDTO.fromEntity(stadiumService.updateStadium(stadiumId,stadiumDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStadium(@PathVariable("id") Long stadiumId){
        if(stadiumService.getById(stadiumId).isPresent()){
            stadiumService.deleteStadiumById(stadiumId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}