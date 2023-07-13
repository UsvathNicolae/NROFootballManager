package com.nro.footballmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private int goalsScored;

    @Column
    private int goalsReceived;

    @Column
    private int victories;

    @Column
    private int defeats;

    @Column
    private int draws;

//    @OneToMany(mappedBy = "team")
//    private List<Player> players;
//
//    @OneToMany(mappedBy = "team1")
//    private List<Game> gamesPlayedAsTeamOne;
//
//    @OneToMany(mappedBy = "team2")
//    private List<Game> gamesPlayedAsTeamTwo;
}
