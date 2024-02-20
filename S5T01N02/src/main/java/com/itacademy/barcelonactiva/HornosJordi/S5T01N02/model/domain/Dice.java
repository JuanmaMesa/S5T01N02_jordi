package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain;

import java.util.Random;
public class Dice {

    Random rand = new Random();
    public int getRandomNumDice(){
        int MAX = 6;
        return rand.nextInt(MAX) + 1;
    }

}
