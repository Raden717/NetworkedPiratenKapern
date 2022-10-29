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

    private final String[] Dice = {
        "SWORD",
        "PARROT",
        "MONKEY",
        "SKULL",
        "COIN",
        "DIAMOND"
    };

    private String name;

    private String[] rolled;

    private int score;

    private int skullCount;

    private int rolls;

    private Cards card;

    private boolean turn;

    private boolean isAlive;

    public Player(String n){
        this.rolled = new String[8];
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

        for(int i = 0;i < 8; i++){
            Random rand = new Random();
            int randomRoll = rand.nextInt(6);
            this.rolled[i] = Dice[randomRoll];
        }

    }
    public void reroll(){
        if(skullCount == 3){
            this.isAlive = false;
        }
        for(int i = 0;i < 8; i++){
            Random rand = new Random();
            int randomRoll = rand.nextInt(6);
            this.rolled[i] = Dice[randomRoll];
        }
    }

    public void setForceDice(String[] newRolled){
        this.rolled = newRolled;
    }

    public void forceUpdateAlive(){
        this.skullCount = 0;
        for(int i = 0; i < 8; i ++){
            //System.out.println(this.rolled[i]);
            if(this.rolled[i].equals("Skull")){
                this.skullCount++;
            }
        }
        System.out.println(this.skullCount);
        if(this.skullCount == 3){
            this.isAlive = false;
        }
    }

    public void setSkulls(int s){
        this.skullCount = 3;
    }

    public boolean getAlive(){
        return this.isAlive;
    }

}
