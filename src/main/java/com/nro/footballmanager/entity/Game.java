package com.nro.footballmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_one_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team_two_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Team team2;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @OneToOne
    @JoinColumn(name = "result_id")
    private Result result;
}
