package com.nro.footballmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_one_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team_two_id")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @Column
    private Time startHour;

    @Column
    private Date date;

    @OneToOne
    @JoinColumn(name = "result_id")
    private Result result;
}
