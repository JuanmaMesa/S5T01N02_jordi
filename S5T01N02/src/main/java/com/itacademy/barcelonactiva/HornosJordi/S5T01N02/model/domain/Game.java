package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Player player;

    @Column(name="firstDice", nullable= false)
    private int dice1;

    @Column(name="secondDice", nullable = false)
    private int dice2;

    @Column(nullable = false, updatable = false, insertable = false)
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp playedTime;

    public Game(Player player, int dice1, int dice2){
        this.player = player;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }
}
