package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.services;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.domain.Player;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {

    public List<PlayerDTO> getAllPlayers();
    public Player getOnePlayer(Integer id);
    public PlayerDTO addPlayer(PlayerDTO sucursalDTO);
    public PlayerDTO updatePlayer(Integer id, PlayerDTO sucursalDTO);
    public void deletePlayer(Integer id);
    public Player findByName(String nameBranch);
    public PlayerDTO findById(Integer id);
}
