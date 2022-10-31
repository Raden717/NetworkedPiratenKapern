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

    private int skullCard;

    private int rolls;

    private int[] keeps;

    private List<String> saves;

    private String card;

    private boolean turn;

    private boolean isAlive;

    private boolean islandOfSkulls;

    private int sorceressUse;

    private int swordBattleReq;

    private int seaBattleDeduction;

    public Player(String n){
        this.seaBattleDeduction = 0;
        this.swordBattleReq = 0;
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
        this.swordBattleReq = 0;
        Random rand = new Random();
        int randomRoll = rand.nextInt(8);
        this.card = cards[randomRoll];
        if(this.card.equals("SEA_BATTLE")){
            this.swordBattleReq = rand.nextInt(3)+2;
        } else if (this.card.equals("SKULL")){
            this.skullCard = rand.nextInt(3)+1;
            this.skullCount = this.skullCard;
        }
    }

    public void roll(){
        this.seaBattleDeduction = 0;
        this.islandOfSkulls = false;
        this.saves = new ArrayList<String>();
        this.keeps = null;
        this.score = 0;
        this.skullCount = 0;
        this.isAlive = true;

        for(int i = 0;i < 8; i++){
            Random rand = new Random();
            int randomRoll = rand.nextInt(6);
            this.rolled[i] = Dice[randomRoll];
        }

    }
    public void reroll(){

        if(islandOfSkulls) {
            for (int i = 0; i < 8; i++) {
                if (!this.rolled[i].toUpperCase().equals("SKULL")) {
                    Random rand = new Random();
                    int randomRoll = rand.nextInt(6);
                    this.rolled[i] = Dice[randomRoll];
                    if(this.rolled[i].equals("SKULL")){
                        this.skullCount++;
                    }
                }
            }
            if(this.card.equals("CAPTAIN")){
                this.skullCount += this.skullCount;
            }
            return;
        }


        if(this.keeps != null) {
            List<Integer> keepRoll = Arrays.stream(this.keeps).boxed().toList();
            for(int i = 0;i < 8; i++){
                 if (!keepRoll.contains(i) && this.rolled[i].toUpperCase().equals("SKULL")) {
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
        this.skullCount = s;
    }

    //Score updates after the turn ends by being called after rerolls/rolls
    public void updateScore() {
        boolean fullChest = false;
        int fullChestCount = 0;
        int scoreToAdd = 0;
        int seaBattlePoints = 0;
        boolean wonSea = false;

        updateAlive();

        if(this.card.equals("MONKEY_BUSINESS")){
            monkeyBusiness();
            return;
        }

        if(this.card.equals("TREASURE_CHEST") && !this.isAlive){
            treasureChestUpdateScore();
        }

        Set<String> rolledDice = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            if (!this.rolled[i].toUpperCase().equals("SKULL")) {
                rolledDice.add(this.rolled[i]);
            }
        }

        if(rolledDice.size() == 1){
            fullChest = true;
        }

        for(int i = 0; i < 8; i++){
            if(this.rolled[i].equals("SKULL")){
                fullChest = false;
            }
        }

        if (!this.isAlive || this.skullCount >= 3) {
                this.islandOfSkulls = false;
                this.saves = new ArrayList<String>();
                this.keeps = null;
                this.rolled = new String[8];
                this.rolls = 8;
                this.skullCount = 0;
                this.card = "";
                this.isAlive = true;
                //Does not add anything to the score and ends turn
                return;
        }


        if(this.card.equals("SEA_BATTLE")){
            Random rand = new Random();
            int swords = swordBattleReq;
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
                seaBattleDeduction = seaBattlePoints;
                if(this.score < 0){
                    this.score = 0;
                }
                return;
            } else {
                scoreToAdd += seaBattlePoints;
                wonSea = true;
            }

        }


        if(this.card.equals("GOLD") || this.card.equals("DIAMOND")){
            scoreToAdd += 100;
        }

        for (String check : rolledDice) {
            int count = 0;
            if(check.equals("COIN") && this.card.equals("GOLD")){
                count++;
                fullChestCount--;
            }
            if(check.equals("DIAMOND") && this.card.equals("DIAMOND")){
                count++;
                fullChestCount--;
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
                else if(check.equals("SWORD") && wonSea){
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
                case 9:
                    scoreToAdd+= 4000;
                    break;
            }
        }
        if(fullChestCount == 8 || fullChest){
            scoreToAdd += 500;
        }
        if(this.card.equals("CAPTAIN")){
            scoreToAdd += scoreToAdd;
        }

        this.score += scoreToAdd;
        this.islandOfSkulls = false;
        this.saves = new ArrayList<String>();
        this.keeps = null;
        this.rolled = new String[8];
        this.rolls = 8;
        this.skullCount = 0;
        this.card = "";
        this.isAlive = true;

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
                    if(this.saves.get(i).equals("COIN")){
                        this.score += 100;
                    }
                    if(this.saves.get(i).equals("DIAMOND")){
                        this.score += 100;
                    }
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
        int fullChestCount = 0;
        int scoreToAdd = 0;
        int seaBattlePoints = 0;
        if(!this.isAlive){
            this.islandOfSkulls = false;
            this.saves = new ArrayList<String>();
            this.keeps = null;
            this.rolled = new String[8];
            this.rolls = 8;
            this.skullCount = 0;
            this.card = "";
            this.isAlive = true;
            return;
        }
        Set<String> rolledDice = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            if(!this.rolled[i].toUpperCase().equals("SKULL")) {
                rolledDice.add(this.rolled[i]);
            } else {
                this.skullCount++;
            }
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
            } else {
                for (int i = 0; i < 8; i++) {
                    if (check.equals(this.rolled[i])) {
                        monkeyParrots++;
                    }
                }

            }

        }

        switch (monkeyParrots) {
            case 3:
                fullChestCount += monkeyParrots;
                System.out.println("HERE?");
                scoreToAdd += 100;
                break;
            case 4:
                fullChestCount += monkeyParrots;
                scoreToAdd += 200;
                break;
            case 5:
                fullChestCount += monkeyParrots;
                scoreToAdd += 500;
                break;
            case 6:
                fullChestCount += monkeyParrots;
                scoreToAdd += 1000;
                break;
            case 7:
                fullChestCount += monkeyParrots;
                scoreToAdd += 2000;
                break;
            case 8:
                fullChestCount += monkeyParrots;
                scoreToAdd += 4000;
                break;
        }

        if(fullChestCount == 8){
            this.score += 500;
        }
        this.score += scoreToAdd;
        //Update score
        this.islandOfSkulls = false;
        this.saves = new ArrayList<String>();
        this.keeps = null;
        this.rolled = new String[8];
        this.rolls = 8;
        this.skullCount = 0;
        this.card = "";
        this.isAlive = true;


    }

    public void updateAlive(){
        this.skullCount = 0;
        this.skullCount += skullCard;
        for (int i = 0; i < 8; i++){
            if(this.rolled[i].equals("SKULL")){
                this.skullCount++;
            }
        }
        if(this.skullCount >= 3){
            this.isAlive = false;
        }
        if(this.skullCount > 3){
            this.islandOfSkulls = true;
        }

        if(this.card.equals("CAPTAIN")){
            this.skullCount += this.skullCount;
        }


    }
    public void deductScore(int skulls){
        this.score -= (skulls * 100);
        if(this.score < 0){
            this.score = 0;
        }
    }

    public void keep(int[] k){
        this.keeps = k;
    }

    public void save(int[] s){
        this.saves.clear();
        for(int i = 0; i< s.length ;i++){
            this.saves.add(this.rolled[s[i]]);
        }
    }

    public void setCard(String c){
        this.card = c;
        Random rand = new Random();
        if(this.card.equals("SEA_BATTLE")){
            this.swordBattleReq = rand.nextInt(3)+2;
        } else if (this.card.equals("SKULL")){
            this.skullCard = rand.nextInt(3)+1;
            this.skullCount = this.skullCard;
        }
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

    public int getSkullCount(){
        return this.skullCount;
    }

    public String getName(){
        return this.name;
    }

    public int getSorceressUse(){
        return this.sorceressUse;
    }

    public int getswordBattleReq(){
        return this.swordBattleReq;
    }

    public void setSwordsSea(int n){
        this.swordBattleReq = n;
    }

    public void setSkullFace(int n){
        this.skullCard = n;
    }

    public int getSkullFace(){
        return this.skullCard;
    }

}
