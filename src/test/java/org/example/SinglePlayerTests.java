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
    void unitTestMonkeyBusiness(){
        Player test = new Player("Test");
        test.setCard("MONKEY_BUSINESS");
        String[] forcedRoll = {"PARROT","PARROT","MONKEY","PARROT","MONKEY","PARROT","SKULL","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(1000, test.getScore());
    }

    @Test
    void unitTestSkulls(){
        Player test = new Player("Test");
        test.setCard("SKULL");
        String[] forcedRoll = {"SKULL","SKULL","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(0,test.getScore());

    }

    @Test
    void unitTestFullChest(){
        Player test = new Player("Test");
        test.setCard("SORCERESS"); //Card that won't mess with the points
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","COIN","DIAMOND","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(900, test.getScore());
    }

    @Test
    void unitTestSkullDeduction(){
        Player test = new Player("Test");
        test.setCard("SORCERESS");
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedRoll);
        test.updateScore();

        Player test2 = new Player("Test 2");
        test.setCard("MONKEY_BUSINESS");
        String[] forcedRoll2 = {"SKULL","SKULL","SKULL","SKULL","MONKEY","MONKEY","PARROT","PARROT"};
        test2.setForceDice(forcedRoll2);
        test2.updateScore();
        test.deductScore(4); //Assuming networking can send the info of 4 skulls to the other player

        assertEquals(4100, test.getScore());
    }

    @Test
    void unitTestCheckAlive(){
        Player test = new Player("Test");
        test.setCard("CAPTAIN");
        String[] forcedRoll = {"SKULL","SKULL","SKULL","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        assertEquals(false, test.getAlive());

    }

    @Test
    void unitTestSeaCard(){
        Player test = new Player("Test");
        test.rollCard();
        if(test.getCard().equals("SEA_BATTLE")){
            assertTrue(0 != test.getswordBattleReq());
        } else {
            assertEquals(0, test.getswordBattleReq());
        }
    }

    @Test
    void unitTestRollSkull(){
        Player test = new Player("test");
        test.rollCard();
        if(test.getCard().equals("SKULL")){
            assertTrue(0 != test.getSkullCount());
        } else {
            assertEquals(0, test.getSkullCount());
        }
    }

    @Test
    void Test45(){
        Player test = new Player("Test");
        String[] forcedRoll = {"SKULL","SKULL","SKULL","SWORD","SWORD","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        assertEquals(0, test.getScore());
    }

    @Test
    void Test46(){
        //roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll 3 swords, get 2 skulls 1 sword  die
        Player test = new Player("Test");
        test.roll();
        String[] forcedRoll = {"SKULL","PARROT","PARROT","PARROT","PARROT","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll = {"SKULL","PARROT","PARROT","PARROT","PARROT","SKULL","SKULL","SWORD"};
        test.setForceDice(forcedReroll);
        test.updateAlive();
        assertEquals(0, test.getScore());

    }

    @Test
    void Test47(){
        //roll 2 skulls, 4 parrots, 2 swords, reroll swords, get 1 skull 1 sword  die
        Player test = new Player("Test");
        test.roll();
        String[] forcedRoll = {"SKULL","PARROT","PARROT","PARROT","PARROT","SWORD","SWORD","SKULL"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll = {"SKULL","PARROT","PARROT","PARROT","PARROT","SKULL","SWORD","SKULL"};
        test.setForceDice(forcedReroll);
        test.updateAlive();
        test.updateScore();
        assertEquals(0, test.getScore());


    }

    @Test
    void Test49(){
        //roll 1 skull, 4 parrots, 3 swords, reroll swords, get 1 skull 2 monkeys
        //            reroll 2 monkeys, get 1 skull 1 monkey and die
        Player test = new Player("Test");
        test.roll();
        String[] forcedRoll = {"SKULL","PARROT","PARROT","PARROT","PARROT","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll = {"SKULL","PARROT","PARROT","PARROT","PARROT","SKULL","MONKEY","MONKEY"};
        test.setForceDice(forcedReroll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll2 = {"SKULL","PARROT","PARROT","PARROT","PARROT","SKULL","SKULL","MONKEY"};
        test.setForceDice(forcedReroll2);
        test.updateAlive();
        test.updateScore();
        assertEquals(0, test.getScore());
    }

    @Test
    void Test51(){
        //roll 1 skull, 2 parrots, 3 swords, 2 coins, reroll parrots get 2 coins
        //      reroll 3 swords, get 3 coins (SC 4000 for seq of 8 (with FC coin) + 8x100=800 = 4800)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"SKULL","PARROT","PARROT","SWORD","SWORD","SWORD","COIN","COIN"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll = {"SKULL","COIN","COIN","SWORD","SWORD","SWORD","COIN","COIN"};
        test.setForceDice(forcedReroll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll2 = {"SKULL","COIN","COIN","COIN","COIN","COIN","COIN","COIN"};
        test.setForceDice(forcedReroll2);
        test.updateAlive();
        test.updateScore();
        assertEquals(4800, test.getScore());
    }

    @Test
    void Test52(){
        //score first roll with 2 (monkeys/parrot/diamonds/coins) and FC is captain (SC 800)
        Player test = new Player("Test");
        test.setCard("CAPTAIN");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","PARROT","PARROT","DIAMOND","DIAMOND","COIN","COIN"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.updateScore();
        assertEquals(800, test.getScore());
    }

    @Test
    void Test53(){
        //roll 2 (monkeys/skulls/swords/parrots), reroll parrots and get 1 sword & 1 monkey (SC 300 since FC is coin)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","SKULL","SKULL","SWORD","SWORD","PARROT","PARROT"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"MONKEY","MONKEY","SKULL","SKULL","SWORD","SWORD","SWORD","MONKEY"};
        test.setForceDice(forcedReroll);
        test.updateAlive();
        test.updateAlive();
        test.updateScore();
        assertEquals(300, test.getScore());
    }

    @Test
    void Test54(){
        //roll 3 (monkey, swords) + 2 skulls and score   (SC 300)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","SKULL","SKULL","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(300, test.getScore());
    }




}