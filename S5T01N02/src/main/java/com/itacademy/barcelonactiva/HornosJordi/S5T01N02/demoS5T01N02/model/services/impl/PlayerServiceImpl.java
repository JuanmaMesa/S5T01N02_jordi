package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.services.impl;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.exception.GameNotFoundException;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.exception.PlayerNotFoundException;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.exception.UsernameInUsedException;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.domain.Player;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.dto.GameDTO;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.dto.PlayerDTO;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.repository.PlayerRepository;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.services.GameServices;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.services.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;
    private GameServices gameServices;

    public PlayerServiceImpl(PlayerRepository playerRepository, GameServices gameServices){
        this.playerRepository = playerRepository;
        this.gameServices = gameServices;
    }

    @Override
    public Player getPlayer(Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Jugador no encontrado"));
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public GameDTO playGame(Integer id) {
        Player player = getPlayer(id);
        GameDTO gameDTO = gameServices.addGame(player);
        //
        return null;
    }

    @Override
    public List<GameDTO> getAllGames(Integer id) {
        return gameServices.getAllGames(getPlayer(id));
    }

    @Override
    public void deleteAllGames(Integer id) {
        Player player = getPlayer(id);
        gameServices.deleteAllGames(player);
        player.setWinRate(null);
        playerRepository.save(player);
    }

    @Override
    public List<PlayerDTO> getWinRates() {
        List<Player> playersList = getAllPlayers();
        List<PlayerDTO> playersDTOList = new ArrayList<>();
        playersList.forEach(p -> playersDTOList.add(new PlayerDTO(p.getUsername(), p.getWinRate())));
        return playersDTOList;
    }

    @Override
    public double getAVGWinRates() {
        return getWinRates().stream().filter(p -> p.getWinRate() != null)
                .mapToDouble(PlayerDTO::getWinRate).average()
                .orElseThrow(() -> new GameNotFoundException("Juego no encontrado"));
    }

    @Override
    public PlayerDTO getWinner() {
        return getWinRates().stream().filter(p -> p.getWinRate() != null)
                .max(Comparator.comparing(PlayerDTO::getWinRate))
                .orElseThrow(() -> new GameNotFoundException("El jugador no tiene ningun a partida jugada"));
    }

    @Override
    public PlayerDTO getLoser() {
        return getWinRates().stream().filter(p -> p.getWinRate() != null)
                .min(Comparator.comparing(PlayerDTO::getWinRate))
                .orElseThrow(() -> new GameNotFoundException("El jugador no tiene ninguna partida jugada"));

    }

    @Override
    public PlayerDTO player2DTO(Player player) {
        return new PlayerDTO(player.getUsername(), player.getWinRate());
    }

    @Override
    public void updatePlayer(Integer id, PlayerDTO playerDTO) {
        Player player = getPlayer(id);
        Player actualPlayer = playerRepository.findByPlayerName(playerDTO.getUsername());
        if(actualPlayer != null){
            if(player.getId() != actualPlayer.getId()){
                throw new UsernameInUsedException("Nombre de usuario ya registrado");
            }
        }
        player.setUsername(playerDTO.getUsername());
        playerRepository.save(player);
    }

    @Override
    public void addPlayer(PlayerDTO player) {
        playerRepository.save(DTO2Player(player));

    }

    @Override
    public Player DTO2Player(PlayerDTO player) {
        return new Player((player.getUsername()));
    }

    private void updateWinRate(Player player, GameDTO gameDTO){
        float winRate = player.getWinRate();
        float victory = 0;
        int gamesPlayed = gameServices.getAllGames(player).size();
        if(gameDTO.isSeven()){
            victory += 1;
        }

    }
}
