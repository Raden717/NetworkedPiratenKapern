package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    @Test
    void UnitTestFortuneCardPick(){
        Player test = new Player("Test");
        List<String> cards = new ArrayList<String>(Arrays.asList("TREASURE_CHEST", "CAPTAIN", "SORCERESS", "SEA_BATTLE", "GOLD", "DIAMOND", "MONKEY_BUSINESS", "SKULL"));

        test.rollCard();
        assertTrue(cards.contains(test.getCard()));
    }

    @Test
    void UnitTestReroll(){
        Player test = new Player("Test");
        test.roll();
        List<String> rolls = new ArrayList<String>();
        int[] indexkeep = {1,2,3,4};
        test.keep(indexkeep);
        rolls.add(test.getRolled()[1]);
        rolls.add(test.getRolled()[2]);
        rolls.add(test.getRolled()[3]);
        rolls.add(test.getRolled()[4]);
        test.reroll();
        for(int i = 0; i< 4; i++){
            assertTrue(rolls.get(i).equals(test.getRolled()[indexkeep[i]]));
        }


    }

    @Test
    void UnitTestTreasureChest(){

        Player test = new Player("Test");
        test.setCard("TREASURE_CHEST");
        String[] forcedRoll = {"Skull","Parrot","Parrot","Parrot","Parrot","Sword","Sword","Sword"};
        test.setForceDice(forcedRoll);
        int[] indexsave = {1,2,3,4};
        test.save(indexsave);
        String[] forcedRoll2 = {"Skull","Parrot","Parrot","Parrot","Parrot","Skull","Skull","Sword"};
        test.setForceDice(forcedRoll2);
        test.updateScore();
        assertEquals(200, test.getScore());

    }

    @Test
    void UnitTestCaptain(){
        Player test = new Player("Test");
        test.setCard("CAPTAIN");
        String[] forcedRoll = {"Skull","Parrot","Parrot","Parrot","Parrot","Sword","Sword","Sword"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(600, test.getScore());
    }

    @Test
    void UnitTestSorceress(){
        Player test = new Player("Test");
        test.setCard("SORCERESS");
        String[] forcedRoll = {"Skull","Skull","Parrot","Parrot","Parrot","Sword","Sword","Sword"};
        test.setForceDice(forcedRoll);
        int[] keeps = {2,3,4,5,6,7};
        test.keep(keeps);
        test.reroll();
        assertEquals(0,test.getSorceressUse());
    }

    @Test
    void unitTestSeaBattle(){
        Player test = new Player("Test");
        test.setCard("SEA_BATTLE");
        String[] forcedRoll = {"SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertTrue(4000 != test.getScore());
    }

    @Test
    void unitTestGold(){
        Player test = new Player("Test");
        test.setCard("GOLD");
        String[] forcedRoll = {"COIN","COIN","COIN","COIN","MONKEY","PARROT","SKULL","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(1000, test.getScore());

    }

    @Test
    void unitTestDiamond(){
        Player test = new Player("Test");
        test.setCard("DIAMOND");
        String[] forcedRoll = {"DIAMOND","DIAMOND","DIAMOND","COIN","MONKEY","PARROT","SKULL","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(700, test.getScore());

    }

    @Test
    void Test44(){
        Player test = new Player("Test");

        test.setSkulls(3); //Forcing skull count before the roll so the "roll" contains 3 skulls immediately
        test.roll();
        assertEquals(false, test.getAlive());
        assertEquals(0, test.getScore());
    }

    @Test
    void Test45(){
        //roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll 3 swords, get 2 skulls 1 sword  die
        Player test = new Player("Test");
        test.roll();
        String[] forcedRoll = {"Skull","Parrot","Parrot","Parrot","Parrot","Sword","Sword","Sword"};
        test.setForceDice(forcedRoll);
        test.forceUpdateAlive();
        test.reroll();
        String[] forcedReroll = {"Skull","Parrot","Parrot","Parrot","Parrot","Skull","Skull","Sword"};
        test.setForceDice(forcedReroll);
        test.forceUpdateAlive();
        assertEquals(false, test.getAlive());
        assertEquals(0, test.getScore());

    }

    @Test
    void Test46(){
        //roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll 3 swords, get 2 skulls 1 sword  die
        Player test = new Player("Test");
        test.roll();
        String[] forcedRoll = {"Skull","Parrot","Parrot","Parrot","Parrot","Sword","Sword","Sword"};
        test.setForceDice(forcedRoll);
        test.forceUpdateAlive();
        test.reroll();
        String[] forcedReroll = {"Skull","Parrot","Parrot","Parrot","Parrot","Skull","Skull","Sword"};
        test.setForceDice(forcedReroll);
        test.forceUpdateAlive();
        assertEquals(false, test.getAlive());
        test.updateScore();
        assertEquals(0, test.getScore());


    }

    @Test
    void Test47(){
        //roll 1 skull, 4 parrots, 3 swords, reroll swords, get 1 skull 2 monkeys
        //      reroll 2 monkeys, get 1 skull 1 monkey and die
        Player test = new Player("Test");
        test.roll();
        String[] forcedRoll = {"Skull","Parrot","Parrot","Parrot","Parrot","Sword","Sword","Sword"};
        test.setForceDice(forcedRoll);
        test.forceUpdateAlive();
        test.reroll();
        String[] forcedReroll = {"Skull","Parrot","Parrot","Parrot","Parrot","Skull","Monkey","Monkey"};
        test.setForceDice(forcedReroll);
        test.reroll();
        String[] forcedReroll2 = {"Skull","Parrot","Parrot","Parrot","Parrot","Skull","Skull","Monkey"};
        test.setForceDice(forcedReroll2);
        test.updateScore();
        assertEquals(0, test.getScore());
    }



}