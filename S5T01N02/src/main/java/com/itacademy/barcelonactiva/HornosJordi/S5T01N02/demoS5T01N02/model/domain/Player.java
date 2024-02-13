package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {

    @Column(name="username")
    private String username;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float winRate;

    public Player(String username){
        this.username = username;
        this.winRate = null;
    }

}
