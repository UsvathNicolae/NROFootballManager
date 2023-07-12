package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Stadium;
import com.nro.footballmanager.entity.dto.StadiumDTO;
import com.nro.footballmanager.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StadiumServiceImpl implements StadiumService{

    @Autowired
    private StadiumRepository stadiumRepository;

    @Override
    public Stadium saveStadium(Stadium stadium){
        return stadiumRepository.save(stadium);
    }

    @Override
    public List<Stadium> findAll(){
        return stadiumRepository.findAll();
    }

    @Override
    public Optional<Stadium> getById(Long stadiumId) {
        return stadiumRepository.findById(stadiumId);
    }

    @Override
    public Stadium updateStadium(Long stadiumId, StadiumDTO stadiumDTO){
        Stadium stadium = StadiumDTO.toEntity(stadiumDTO);
        stadium.setId(stadiumId);
        return stadiumRepository.save(stadium);
    }

    @Override
    public void deleteStadiumById(Long stadiumId){
        stadiumRepository.deleteById(stadiumId);
    }
}
