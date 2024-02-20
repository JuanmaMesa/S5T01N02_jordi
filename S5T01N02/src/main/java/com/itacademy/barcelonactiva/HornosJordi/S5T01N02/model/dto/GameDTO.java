package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {

    private int dice1;
    private int dice2;

    public GameDTO(int dice1, int dice2){
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    public boolean isSeven(){

        return this.dice1 + this.dice2 == 7;
    }

}
