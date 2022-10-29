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
        //System.out.println(this.skullCount);
        if(this.skullCount == 3){
            this.isAlive = false;
        }
    }

    public void setSkulls(int s){
        this.skullCount = 3;
    }

    //Score updates after the turn ends by being called after rerolls/rolls
    public void updateScore() {
        Set<String> rolledDice = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            if(!this.rolled[i].equals("Skull")) {
                rolledDice.add(this.rolled[i]);
            } else {
                this.skullCount++;
            }
        }
        if(this.skullCount >= 3){
            //Does not add anything to the score;
            return;
        }

        for (String check : rolledDice) {
            int count = 0;
                for (int i = 0; i < 8; i++) {
                    if (check.equals(this.rolled[i])) {
                        count++;
                    }
                }

            //3 of a kind: 100 points; 4 of a kind: 200 points; 5 of a kind: 500
            //points; 6 of a kind: 1,000 points; 7 of a kind: 2,000 points; 8 of a kind:
            //4,000 points.
            switch (count) {
                case 3:
                    this.score += 100;
                    break;
                case 4:
                    this.score += 200;
                    break;
                case 5:
                    this.score += 500;
                    break;
                case 6:
                    this.score += 1000;
                    break;
                case 7:
                    this.score += 2000;
                    break;
                case 8:
                    this.score += 4000;
                    break;
            }
        }
        //Update score
    }

    public int getScore(){
        //Returns score
        return this.score;
    }

    public boolean getAlive(){
        return this.isAlive;
    }

}
