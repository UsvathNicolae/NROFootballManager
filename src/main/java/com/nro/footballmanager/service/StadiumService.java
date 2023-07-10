package com.nro.footballmanager.service;

import com.nro.footballmanager.entity.Stadium;

import java.util.List;

public interface StadiumService {
    Stadium saveStadium(Stadium stadium);

    List<Stadium> fetchStadiumsList();

    Stadium updateStadium(Stadium stadium, Long stadiumId);

    void deleteStadium(Long stadiumId);
}
