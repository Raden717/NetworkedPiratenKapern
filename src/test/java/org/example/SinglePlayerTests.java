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

    void UnitTestReroll(){
        Player test = new Player("Test");
        test.roll();
        List<String> rolls = new ArrayList<String>();
        test.keep([1,2,3,4]);
        rolls.add(test.getRolled[1]);
        rolls.add(test.getRolled[2]);
        rolls.add(test.getRolled[3]);
        rolls.add(test.getRolled[4]);
        test.reroll();
        for(int i = 0; i< 4; i++){
            assertTrue(rolls.get(i).equals(test.getRolled[i]);
        }


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