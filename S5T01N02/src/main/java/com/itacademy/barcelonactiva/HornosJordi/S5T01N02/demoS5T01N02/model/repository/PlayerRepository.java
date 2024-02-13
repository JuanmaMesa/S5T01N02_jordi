package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.repository;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    public Player findByPlayerName(String playerName);
}
