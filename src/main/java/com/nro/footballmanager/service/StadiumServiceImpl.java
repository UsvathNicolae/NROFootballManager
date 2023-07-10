package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Stadium;
import com.nro.footballmanager.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumServiceImpl implements StadiumService{

    @Autowired
    private StadiumRepository stadiumRepository;

    @Override
    public Stadium saveStadium(Stadium stadium){
        return stadiumRepository.save(stadium);
    }

    @Override
    public List<Stadium> fetchStadiumsList(){
        return stadiumRepository.findAll();
    }

    @Override
    public Stadium updateStadium(Stadium stadium, Long stadiumId){
        Stadium stadiumDB = stadiumRepository.findById(stadiumId).get();

        if(stadium.getName() != null && !"".equalsIgnoreCase(stadium.getName())){
            stadiumDB.setName(stadium.getName());
        }

        if(stadium.getLocation() != null && !"".equalsIgnoreCase(stadium.getLocation())){
            stadiumDB.setLocation(stadium.getLocation());
        }

        if(stadium.getGamesOnStadium() != null ){
            stadiumDB.setGamesOnStadium(stadium.getGamesOnStadium());
        }

        return stadiumRepository.save(stadiumDB);
    }

    @Override
    public void deleteStadium(Long stadiumId){
        stadiumRepository.deleteById(stadiumId);
    }
}
