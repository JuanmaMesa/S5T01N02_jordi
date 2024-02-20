package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.impl;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.exception.GameNotFoundException;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.exception.PlayerNotFoundException;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.exception.UsernameInUsedException;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.Player;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.GameDTO;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.PlayerDTO;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request.PlayerDTORequest;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.repository.PlayerRepository;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.GameServices;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        updateWinRate(player, gameDTO);
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
    public void updatePlayer(Integer id, PlayerDTORequest playerDTO) {
        Player player = getPlayer(id);
        Player playerEntityExisting = playerRepository.findByPlayerName(player.getUsername());
        if(playerEntityExisting != null){
            if(playerEntityExisting.getId() != player.getId()){
                throw new UsernameInUsedException("username no disponible");
            }
        }
        player.setUsername(playerDTO.getUsername());
        playerRepository.save(player);
    }

    @Override
    public void addPlayer(PlayerDTORequest player) {
        playerRepository.save(DTO2Player(player));

    }

    @Override
    public Player DTO2Player(PlayerDTORequest player) {
        return new Player((player.getUsername()));
    }

    private void updateWinRate(Player player, GameDTO gameDTO){
        Float winRate = player.getWinRate();
        float victory = 0;
        if(gameDTO.isSeven()){
            victory = 1.0f;
        }
        else{
            victory = 0.0f;
        }
        if(winRate == null){
            winRate = victory*100;
        }
        else{
            int gamesPlayed = gameServices.getAllGames(player).size();
            int victories = (int) (winRate/(100*gamesPlayed));
            winRate = (victories + victory)*100/(gamesPlayed);
        }
        player.setWinRate(winRate);
        playerRepository.save(player);

    }
}
