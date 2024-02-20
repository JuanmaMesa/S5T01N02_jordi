package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.Game;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.Player;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.GameDTO;

import java.util.List;

public interface GameServices {

    public List<GameDTO> getAllGames(Player player);
    public void deleteAllGames(Player player);
    public GameDTO addGame(Player player);
    public Game DTO2Game(Player player, GameDTO gameDTO);
    public GameDTO Game2DTO(Game game);


}
