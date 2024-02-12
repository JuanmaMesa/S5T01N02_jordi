package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.controller;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.demoS5T01N02.model.services.GameServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("diceapi/v1")
public class GameController {

    private GameServices gameServices;

    @PostMapping("/players")
    //crea jugador

    @PutMapping("/players")
    //modifical el nombre del jugador

    @PostMapping("/players/{id}/games")
    // un jugador específico realiza una tirada de dados

    @DeleteMapping("/players/{id}/games")
    //elimina las tiradas del jugador

    @GetMapping("/players")
    //retorna el listado de todos los jugadores del sistema con su porcentaje de éxito

    @GetMapping("/players/{id}/games")
    //retorna el listado de jugadas de un jugador

    @GetMapping("/players/ranking")
    //retorna el porcentaje medio de éxitos

    @GetMapping("/players/ranking/loser")
    //retorna el jugador con peor porcentaje de éxito

    @GetMapping("/players/ranking/winner")
    //retorna el jugador con mejor porcentaje de éxito


}
