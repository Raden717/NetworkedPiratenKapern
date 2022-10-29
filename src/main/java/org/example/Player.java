package org.example;

import java.util.*;

public class Player {

    enum Cards{
        TREASURE_CHEST,
        CAPTAIN,
        SORCERESS,
        SEA_BATTLE,
        GOLD,
        DIAMOND,
        MONKEY_BUSINESS,
        SKULL
    }

    enum Dice{
        SWORD,
        PARROT,
        MONKEY,
        SKULL,
        COIN,
        DIAMOND
    }

    private String name;

    private int score;

    private int skullCount;

    private int rolls;

    private Cards card;

    private boolean turn;

    private boolean isAlive;

    public Player(String n){
        this.rolls = 8;
        this.name = n;
        this.score = 0;
        this.skullCount = 0;
        this.card = null;
        this.turn = false;
        this.isAlive = true;
    }

    public void roll(){
        if(skullCount == 3){
            this.isAlive = false;
        }
        //create a list of dice rolls
    }

    public void setSkulls(int s){
        this.skullCount = 3;
    }

    public boolean getAlive(){
        return this.isAlive;
    }

}
