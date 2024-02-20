package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.repository;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findByPlayerName(String playerName);

}
