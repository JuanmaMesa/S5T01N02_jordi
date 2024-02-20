package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PlayerDTO {

    private String username;
    private Float winRate;

    public PlayerDTO(String username, Float winRate){
        this.username = Objects.requireNonNull(username, "UNKNOWN");
    }
}
