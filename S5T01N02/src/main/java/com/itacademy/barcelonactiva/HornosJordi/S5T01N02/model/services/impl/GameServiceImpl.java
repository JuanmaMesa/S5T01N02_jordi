package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.impl;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.Dice;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.Game;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.Player;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.GameDTO;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.repository.GameRepository;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.GameServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameServices {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    private GameDTO startGame(){
        Dice dice = new Dice();
        return new GameDTO(dice.getRandomNumDice(), dice.getRandomNumDice());
    }

    @Override
    public List<GameDTO> getAllGames(Player player) {
        List<Game> gamesList = gameRepository.findByPlayer(player);
        List<GameDTO> gaDTOList = new ArrayList<>();
        gamesList.stream().toList().forEach(g -> gaDTOList.add(Game2DTO(g)));
        return gaDTOList;
    }

    @Override
    public void deleteAllGames(Player player) {
        List<Game> games = gameRepository.findByPlayer(player);
        games.forEach((gameRepository::delete));

    }

    @Override
    public GameDTO addGame(Player player) {
        GameDTO gameDTO = startGame();
        gameRepository.save(DTO2Game(player, gameDTO));
        return gameDTO;
    }

    @Override
    public Game DTO2Game(Player player, GameDTO gameDTO) {
        return new Game(player, gameDTO.getDice1(), gameDTO.getDice2());
    }

    @Override
    public GameDTO Game2DTO(Game game) {
        return new GameDTO(game.getDice1(), game.getDice2());
    }
}
