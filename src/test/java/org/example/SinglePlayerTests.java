package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglePlayerTests {

    @Test
    void Test44(){
        Player test = new Player("Test");

        test.setSkulls(3); //Forcing skull count before the roll so the "roll" contains 3 skulls immediately
        test.roll();
        assertEquals(false, test.getAlive());
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

    }

}