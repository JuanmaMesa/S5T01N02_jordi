package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.controller;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.GameDTO;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.PlayerDTO;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request.PlayerDTORequest;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("diceapi/v1/diceGame")
public class GameController {

    private final PlayerService playerService;

    public GameController(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping("/players")
    public ResponseEntity<String> newPlayer(@RequestBody PlayerDTORequest playerDTO){
        playerService.addPlayer(playerDTO);
        return new ResponseEntity<>("Jugador creado correctamente", HttpStatus.CREATED);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<String> modificarPlayer(@PathVariable("id") Integer id, @RequestBody PlayerDTORequest playerDTO){
        playerService.updatePlayer(id, playerDTO);
        return new ResponseEntity<>("Jugador modificado correctamente", HttpStatus.OK);
    }

    @PostMapping("/players/{id}/games")
    public ResponseEntity<GameDTO> play(@PathVariable("id") Integer id){
        GameDTO game = playerService.playGame(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @DeleteMapping("/players/{id}/games")
    public ResponseEntity<String> deletePlayerGames(@PathVariable("id") Integer id){
        playerService.deleteAllGames(id);
        return new ResponseEntity<>("Las partidas del jugador han sido eliminadas correctamente", HttpStatus.OK);
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerDTO>> getWinRates(){
        List<PlayerDTO> rateList = playerService.getWinRates();
        return new ResponseEntity<>(rateList, HttpStatus.OK);
    }
    @GetMapping("/players/{id}/games")
    public ResponseEntity<List<GameDTO>> getPlayerGames(@PathVariable("id") Integer id){
        List<GameDTO> games = playerService.getAllGames(id);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/players/ranking")
    public ResponseEntity<Double> getRankingAvg(){
        double avg = playerService.getAVGWinRates();
        return new ResponseEntity<>(avg, HttpStatus.OK);
    }

    @GetMapping("/players/ranking/loser")
    public ResponseEntity<PlayerDTO> getLoser(){
        PlayerDTO loser = playerService.getLoser();
        return new ResponseEntity<>(loser, HttpStatus.OK);
    }

    @GetMapping("/players/ranking/winner")
    public ResponseEntity<PlayerDTO> getWinner(){
        PlayerDTO winner = playerService.getWinner();
        return new ResponseEntity<>(winner, HttpStatus.OK);
    }


}
