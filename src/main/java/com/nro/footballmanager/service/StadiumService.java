package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Stadium;
import com.nro.footballmanager.entity.dto.StadiumDTO;

import java.util.List;
import java.util.Optional;

public interface StadiumService {

    List<Stadium> findAll();

    Optional<Stadium> getById(Long stadiumId);

    Stadium saveStadium(Stadium stadium);


    Stadium updateStadium(Long stadiumId, StadiumDTO stadiumDTO );

    void deleteStadiumById(Long stadiumId);
}
