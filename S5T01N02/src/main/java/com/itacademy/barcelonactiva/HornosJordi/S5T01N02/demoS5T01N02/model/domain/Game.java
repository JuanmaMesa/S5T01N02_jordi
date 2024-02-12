package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="firstDice")
    private Integer dice1;

    @Column(name="secondDice")
    private Integer dice2;
}
