package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Player {

    private final String[] cards = {
            "TREASURE_CHEST",
            "CAPTAIN",
            "SORCERESS",
            "SEA_BATTLE",
            "GOLD",
            "DIAMOND",
            "MONKEY_BUSINESS",
            "SKULL"
    };

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

    private int[] keeps;

    private List<String> saves;

    private String card;

    private boolean turn;

    private boolean isAlive;

    private boolean islandOfSkulls;

    private int sorceressUse;

    public Player(String n){
        this.islandOfSkulls = false;
        this.sorceressUse = 1;
        this.saves = new ArrayList<String>();
        this.keeps = null;
        this.rolled = new String[8];
        this.rolls = 8;
        this.name = n;
        this.score = 0;
        this.skullCount = 0;
        this.card = "";
        this.turn = false;
        this.isAlive = true;
    }

    public void rollCard(){
        Random rand = new Random();
        int randomRoll = rand.nextInt(8);
        this.card = cards[randomRoll];
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

        if(this.keeps != null) {
            List<Integer> keepRoll = Arrays.stream(this.keeps).boxed().toList();
            for(int i = 0;i < 8; i++){
                 if (this.rolled[i].toUpperCase().equals("SKULL")) {
                    if (this.card.equals("SORCERESS") && this.sorceressUse == 1) {
                        Random rand = new Random();
                        int randomRoll = rand.nextInt(6);
                        this.rolled[i] = Dice[randomRoll];
                        this.sorceressUse = 0;
                    }
                }
                if(!keepRoll.contains(i) && !this.rolled[i].toUpperCase().equals("SKULL")) {
                    Random rand = new Random();
                    int randomRoll = rand.nextInt(6);
                    this.rolled[i] = Dice[randomRoll];
                }

            }
        } else {
            for (int i = 0; i < 8; i++) {
                if (!this.rolled[i].toUpperCase().equals("SKULL")) {
                    Random rand = new Random();
                    int randomRoll = rand.nextInt(6);
                    this.rolled[i] = Dice[randomRoll];
                } else {
                    if(this.card.equals("SORCERESS") && this.sorceressUse == 1){
                        Random rand = new Random();
                        int randomRoll = rand.nextInt(6);
                        this.rolled[i] = Dice[randomRoll];
                        this.sorceressUse = 0;
                    }
                }

            }
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
        int fullChestCount = 0;
        int scoreToAdd = 0;
        int seaBattlePoints = 0;

        if(this.card.equals("MONKEY_BUSINESS")){
            monkeyBusiness();
            return;
        }

        if(this.card.equals("SKULL")){
            Random rand = new Random();
            int ranSkull = rand.nextInt(3)+1;
            this.skullCount += ranSkull;
        }

        if(this.card.equals("SEA_BATTLE")){
            Random rand = new Random();
            int swords = rand.nextInt(4)+1;
            int swordCount = 0;

            switch (swords) {
                case 1:
                    seaBattlePoints += 100;
                    break;
                case 2:
                    seaBattlePoints += 300;
                    break;
                case 3:
                    seaBattlePoints += 500;
                    break;
                case 4:
                    seaBattlePoints += 1000;
                    break;
            }

            for (int i = 0; i < 8; i++) {
                if (this.rolled[i].equals("SWORD")) {
                    swordCount++;
                }
            }

            if(swordCount < swords){
                this.score -= seaBattlePoints;
                if(this.score < 0){
                    this.score = 0;
                }
                return;
            } else {
                scoreToAdd += seaBattlePoints;
            }

        }

        if(this.card.equals("TREASURE_CHEST")){
            treasureChestUpdateScore();
        }

        Set<String> rolledDice = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            if(!this.rolled[i].toUpperCase().equals("SKULL")) {
                rolledDice.add(this.rolled[i]);
            } else {
                this.skullCount++;
            }
        }
        if(this.skullCount == 3){
            //Does not add anything to the score and ends turn
            return;
        }
        if(this.skullCount > 3){
            this.islandOfSkulls = true; //If this is true, network checks this after score updates to then deduct point of other players
            return;
        }

        for (String check : rolledDice) {
            int count = 0;
            if(check.equals("COIN") && this.card.equals("GOLD")){
                count++;
                scoreToAdd += 100;
            }
            if(check.equals("DIAMOND") && this.card.equals("DIAMOND")){
                count++;
                scoreToAdd += 100;
            }
            for (int i = 0; i < 8; i++) {
                if (check.equals(this.rolled[i])) {
                    if(this.rolled[i].equals("COIN")){
                        scoreToAdd += 100;
                    }
                    if(this.rolled[i].equals("DIAMOND")){
                        scoreToAdd += 100;
                    }
                    count++;
                }
            }


            //3 of a kind: 100 points; 4 of a kind: 200 points; 5 of a kind: 500
            //points; 6 of a kind: 1,000 points; 7 of a kind: 2,000 points; 8 of a kind:
            //4,000 points.
            if(count < 3){
                if(check.equals("DIAMOND") || check.equals("COIN")){
                    fullChestCount += count;
                }
            }
            switch (count) {
                case 3:
                    fullChestCount += count;
                    scoreToAdd += 100;
                    break;
                case 4:
                    fullChestCount += count;
                    scoreToAdd += 200;
                    break;
                case 5:
                    fullChestCount += count;
                    scoreToAdd += 500;
                    break;
                case 6:
                    fullChestCount += count;
                    scoreToAdd += 1000;
                    break;
                case 7:
                    fullChestCount += count;
                    scoreToAdd += 2000;
                    break;
                case 8:
                    fullChestCount += count;
                    scoreToAdd += 4000;
                    break;
            }
        }
        if(fullChestCount == 8){
            scoreToAdd += 500;
        }
        if(this.card.equals("CAPTAIN")){
            scoreToAdd += scoreToAdd;
        }

        this.score += scoreToAdd;
        //Update score
    }

    public void treasureChestUpdateScore() {

        Set<String> savedDice = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            if (!this.rolled[i].equals("Skull")) {
                savedDice.add(this.rolled[i]);
            }
        }

        for (String check : savedDice){
            int count = 0;
            for(int i = 0; i < this.saves.size(); i++){
                if(check.equals(this.saves.get(i))){
                    count++;
                }
            }
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
    }

    public void monkeyBusiness(){
        int scoreToAdd = 0;
        int seaBattlePoints = 0;

        Set<String> rolledDice = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            if(!this.rolled[i].toUpperCase().equals("SKULL")) {
                rolledDice.add(this.rolled[i]);
            } else {
                this.skullCount++;
            }
        }

        if(this.skullCount == 3){
            return;
        }

        int monkeyParrots = 0;
        for (String check : rolledDice) {
            if(!check.equals("PARROT") && !check.equals("MONKEY")) {
                int count = 0;
                for (int i = 0; i < 8; i++) {
                    if (check.equals(this.rolled[i])) {
                        if (this.rolled[i].equals("COIN")) {
                            scoreToAdd += 100;
                        }
                        if (this.rolled[i].equals("DIAMOND")) {
                            scoreToAdd += 100;
                        }
                        count++;
                    }
                }

                switch (count) {
                    case 3:
                        scoreToAdd += 100;
                        break;
                    case 4:
                        scoreToAdd += 200;
                        break;
                    case 5:
                        scoreToAdd += 500;
                        break;
                    case 6:
                        scoreToAdd += 1000;
                        break;
                    case 7:
                        scoreToAdd += 2000;
                        break;
                    case 8:
                        scoreToAdd += 4000;
                        break;
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    if (check.equals(this.rolled[i])) {
                        monkeyParrots++;
                    }
                }
                switch (monkeyParrots) {
                    case 3:
                        scoreToAdd += 100;
                        break;
                    case 4:
                        scoreToAdd += 200;
                        break;
                    case 5:
                        scoreToAdd += 500;
                        break;
                    case 6:
                        scoreToAdd += 1000;
                        break;
                    case 7:
                        scoreToAdd += 2000;
                        break;
                    case 8:
                        scoreToAdd += 4000;
                        break;
                }
            }
        }
        this.score += scoreToAdd;
        //Update score

    }

    public void updateAlive(){
        for (int i = 0; i < 8; i++){
            if(this.rolled[i].toUpperCase().equals("SKULL")){
                this.skullCount++;
            }
        }
        if(skullCount >= 3){
            this.isAlive = false;
        }
    }
    public void deductScore(int skulls){
        this.score -= (skulls * 100);
    }

    public void keep(int[] k){
        this.keeps = k;
    }

    public void save(int[] s){
        for(int i = 0; i< s.length ;i++){
            this.saves.add(this.rolled[s[i]]);
        }
    }

    public void setCard(String c){
        this.card = c;
    }

    public String[] getRolled(){
        return this.rolled;
    }
    public String getCard(){
        return this.card;
    }
    public int getScore(){
        //Returns score
        return this.score;
    }

    public boolean getislandofSkulls(){
        return this.islandOfSkulls;
    }

    public boolean getAlive(){
        return this.isAlive;
    }

    public int getRolls() {
        return this.rolls;
    }

    public String getName(){
        return this.name;
    }

    public int getSorceressUse(){
        return this.sorceressUse;
    }

}
