package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.services;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.domain.Game;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.domain.Player;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.dto.GameDTO;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {

    public Player getPlayer(Integer id);
    public List<Player> getAllPlayers();
    public GameDTO playGame(Integer id);
    public List<GameDTO> getAllGames(Integer id);
    public void deleteAllGames(Integer id);
    public List<PlayerDTO> getWinRates();
    public double getAVGWinRates();
    public PlayerDTO getWinner();
    public PlayerDTO getLoser();
    public PlayerDTO player2DTO(Player player);
    public void updatePlayer(Integer id, PlayerDTO player);
    public void addPlayer(PlayerDTO player);
    public Player DTO2Player(PlayerDTO player);
    
}
