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

    @Test
    void Test55(){
        //roll 3 diamonds, 2 skulls, 1 monkey, 1 sword, 1 parrot, score (diamonds = 100 + 300 points)   (SC 500)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"DIAMOND","DIAMOND","DIAMOND","SKULL","SKULL","MONKEY","SWORD","PARROT"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(500, test.getScore());
    }

    @Test
    void Test56(){
        //roll 4 coins, 2 skulls, 2 swords and score (coins: 200 + 400 points) with FC is a diamond (SC 700)
        Player test = new Player("Test");
        test.setCard("DIAMOND");
        test.roll();
        String[] forcedRoll = {"COIN","COIN","COIN","COIN","SWORD","SKULL","SWORD","SKULL"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(700, test.getScore());
    }

    @Test
    void Test57(){
        //roll 3 swords, 4 parrots, 1 skull and score (SC 100+200+100= 400)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"SWORD","SWORD","SWORD","PARROT","PARROT","PARROT","PARROT","SKULL"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(400, test.getScore());
    }

    @Test
    void Test58(){
        //roll 1 skull, 2 coins/parrots & 3 swords, reroll parrots,
        // get 1 coin and 1 sword, score (SC = 200+400+200 = 800)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"COIN","COIN","SWORD","PARROT","PARROT","SWORD","SWORD","SKULL"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"COIN","COIN","SWORD","COIN","SWORD","SWORD","SWORD","SKULL"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(800, test.getScore());
    }

    @Test
    void Test59(){
        //same as previous row but with captain fortune card  (SC = (100 + 300 + 200)*2 = 1200)
        Player test = new Player("Test");
        test.setCard("CAPTAIN");
        test.roll();
        String[] forcedRoll = {"COIN","COIN","SWORD","PARROT","PARROT","SWORD","SWORD","SKULL"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"COIN","COIN","SWORD","COIN","SWORD","SWORD","SWORD","SKULL"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(1200, test.getScore());
    }

    @Test
    void Test61(){
        //roll 1 skull, 2 (monkeys/parrots) 3 swords, reroll 2 monkeys, get 1 skull 1 sword,
        //         then reroll parrots get 1 sword 1 monkey (SC 600)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"SKULL","MONKEY","MONKEY","PARROT","PARROT","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"SKULL","SKULL","SWORD","PARROT","PARROT","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedReroll);
        test.reroll();
        String[] forcedReroll2 = {"SKULL","SKULL","SWORD","SWORD","MONKEY","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedReroll2);
        test.updateScore();
        assertEquals(600, test.getScore());
    }

    @Test
    void Test62(){
        //score set of 6 monkeys and 2 skulls on first roll (SC 1100)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"SKULL","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","SKULL"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(1100, test.getScore());
    }

    @Test
    void Test63(){
        //score set of 7 parrots and 1 skull on first roll (SC 2100)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"PARROT","PARROT","PARROT","PARROT","PARROT","PARROT","PARROT","SKULL"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(2100, test.getScore());

    }

    @Test
    void Test64(){
        //score set of 8 coins on first roll (SC 5400)
        // seq of 8 + 9 coins(FC is coin) +  full chest  (no extra points for 9 coins)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"COIN","COIN","COIN","COIN","COIN","COIN","COIN","COIN"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(5400, test.getScore());
    }

    @Test
    void Test65(){
        //score set of 8 coins on first roll and FC is diamond (SC 5400)
        Player test = new Player("Test");
        test.setCard("DIAMOND");
        test.roll();
        String[] forcedRoll = {"COIN","COIN","COIN","COIN","COIN","COIN","COIN","COIN"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(5400, test.getScore());
    }

    @Test
    void Test66(){
        //score set of 8 swords on first roll and FC is captain (SC 4500x2 = 9000) since full chest
        Player test = new Player("Test");
        test.setCard("CAPTAIN");
        test.roll();
        String[] forcedRoll = {"SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(9000, test.getScore());
    }

    @Test
    void Test67(){
        //roll 6 monkeys and 2 swords, reroll swords, get 2 monkeys, score (SC 4600 because of FC is coin and full chest)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(4600, test.getScore());
    }

    @Test
    void Test68(){
        //roll 2 (monkeys/skulls/swords/parrots), reroll parrots, get 2 diamonds, score with FC is diamond (SC 400)
        Player test = new Player("Test");
        test.setCard("DIAMOND");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","SKULL","SKULL","PARROT","PARROT","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"MONKEY","MONKEY","SKULL","SKULL","DIAMOND","DIAMOND","SWORD","SWORD"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(400, test.getScore());
    }

    @Test
    void Test69(){
        //roll 2 (monkeys, skulls, swords), 1 diamond, 1 parrot, reroll 2 monkeys, get 2 diamonds, score 500
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","SKULL","SKULL","DIAMOND","PARROT","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"DIAMOND","DIAMOND","SKULL","SKULL","DIAMOND","PARROT","SWORD","SWORD"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(500, test.getScore());
    }

    @Test
    void Test70(){
        //roll 1 skull, 2 coins, 1 (monkey/parrot), 3 swords, reroll 3 swords, get 1 (coin/monkey/parrot)  (SC 600)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"SKULL","COIN","COIN","MONKEY","PARROT","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"SKULL","COIN","COIN","MONKEY","PARROT","COIN","MONKEY","PARROT"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(600, test.getScore());
    }

    @Test
    void Test71(){
        //roll 1 skull, 2 coins, 1 (monkey/parrot), 3 swords, reroll swords, get 1 (coin/monkey/parrot) with FC is diamond (SC 500)
        Player test = new Player("Test");
        test.setCard("DIAMOND");
        test.roll();
        String[] forcedRoll = {"SKULL","COIN","COIN","MONKEY","PARROT","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"SKULL","COIN","COIN","MONKEY","PARROT","COIN","MONKEY","PARROT"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(500, test.getScore());
    }

    @Test
    void Test72(){
        //get 4 monkeys, 2 coins and 2 skulls with FC coin. Score 600
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","MONKEY","COIN","COIN","SKULL","SKULL"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(600, test.getScore());

    }
    @Test
    void Test77(){
       //roll 2 diamonds, 1 (sword/monkey/coin), 3 parrots, reroll 3 parrots
        // , get 1 skull, 2 monkeys, reroll skull, get monkey (SC 500)
        Player test = new Player("Test");
        test.setCard("SORCERESS");
        test.roll();
        String[] forcedRoll = {"DIAMOND","DIAMOND","SWORD","MONKEY","COIN","PARROT","PARROT","PARROT"};
        test.setForceDice(forcedRoll);
        test.reroll(); //Reroll accounts for the sorceress
        String[] forcedReroll = {"DIAMOND","DIAMOND","SWORD","MONKEY","COIN","SKULL","MONKEY","MONKEY"};
        test.setForceDice(forcedReroll);
        assertEquals(1,test.getSorceressUse()); // Sorceress use should be 1
        test.reroll(); //Reroll function accounts for the sorceress and gets used since there is a skull that can be rerolled
        String[] forcedReroll2 = {"DIAMOND","DIAMOND","SWORD","MONKEY","COIN","MONKEY","MONKEY","MONKEY"};
        assertEquals(0,test.getSorceressUse()); // Sorceress use should be 0
        test.setForceDice(forcedReroll2);
        test.updateScore();
        assertEquals(500, test.getScore());
    }
    @Test
    void Test78(){
        //  roll 3 skulls, 3 parrots, 2 swords, reroll skull, get parrot,
        //  reroll 2 swords, get parrots, score (SC 1000)
        Player test = new Player("Test");
        test.setCard("SORCERESS");
        test.roll();
        String[] forcedRoll = {"SKULL","SKULL","SKULL","PARROT","PARROT","PARROT","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        assertEquals(1,test.getSorceressUse()); // Sorceress use should be 1
        test.reroll(); //Reroll function accounts for the sorceress and gets used since there is a skull that can be rerolled
        assertEquals(0,test.getSorceressUse()); // Sorceress use should be 0
        String[] forcedReroll = {"SKULL","SKULL","PARROT","PARROT","PARROT","PARROT","SWORD","SWORD"};
        test.setForceDice(forcedReroll);
        test.reroll();
        String[] forcedReroll2 = {"SKULL","SKULL","PARROT","PARROT","PARROT","PARROT","PARROT","PARROT"};
        test.setForceDice(forcedReroll2);
        test.updateScore();
        assertEquals(1000, test.getScore());
    }

    @Test
    void Test79(){
        //roll 1 skull, 4 parrots, 3 monkeys, reroll 3 monkeys, get 1 skull, 2 parrots,
        // reroll skull, get parrot, score (SC 2000)
        Player test = new Player("Test");
        test.setCard("SORCERESS");
        test.roll();
        String[] forcedRoll = {"SKULL","PARROT","PARROT","PARROT","PARROT","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedRoll);
        assertEquals(1,test.getSorceressUse()); // Sorceress use should be 1
        int[] keepSkull = {0};
        test.keep(keepSkull);
        test.reroll(); //Reroll function accounts for the sorceress and gets used since there is a skull that can be rerolled
        String[] forcedReroll = {"SKULL","PARROT","PARROT","PARROT","PARROT","SKULL","PARROT","PARROT"};
        test.setForceDice(forcedReroll);
        assertEquals(1,test.getSorceressUse()); // Sorceress use should be 1
        test.reroll();
        assertEquals(0,test.getSorceressUse()); // Sorceress use should be 0
        String[] forcedReroll2 = {"PARROT","PARROT","PARROT","PARROT","PARROT","SKULL","PARROT","PARROT"};
        test.setForceDice(forcedReroll2);
        test.updateScore();
        assertEquals(2000, test.getScore());
    }

    @Test
    void Test82(){
        //roll 3 monkeys 3 parrots  1 skull 1 coin  SC = 1100  (i.e., sequence of of 6 + coin)
        Player test = new Player("Test");
        test.setCard("MONKEY_BUSINESS");
        test.roll();
        String[] forcedRoll = {"SKULL","PARROT","PARROT","PARROT","COIN","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(1100, test.getScore());
    }

    @Test
    void Test83(){
        //roll 2 (monkeys/swords/parrots/coins), reroll 2 swords, get 1 monkey, 1 parrot, score 1700
        Player test = new Player("Test");
        test.setCard("MONKEY_BUSINESS");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","PARROT","PARROT","SWORD","SWORD","COIN","COIN"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"MONKEY","MONKEY","PARROT","PARROT","MONKEY","PARROT","COIN","COIN"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(1700, test.getScore());
    }

    @Test
    void Test84(){
        //roll 3 skulls, 3 monkeys, 2 parrots => die scoring 0
        Player test = new Player("Test");
        test.setCard("MONKEY_BUSINESS");
        test.roll();
        String[] forcedRoll = {"SKULL","SKULL","SKULL","PARROT","PARROT","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(0, test.getScore());
    }

    @Test
    void Test90(){
        //roll 3 parrots, 2 swords, 2 diamonds, 1 coin     put 2 diamonds and 1 coin in chest
        //  then reroll 2 swords and get 2 parrots put 5 parrots in chest and take out 2 diamonds & coin
        //  then reroll the 3 dice and get 1 skull, 1 coin and a parrot
        // 1100
        Player test = new Player("Test");
        test.setCard("TREASURE_CHEST");
        test.roll();
        String[] forcedRoll = {"PARROT","PARROT","PARROT","SWORD","SWORD","DIAMOND","DIAMOND","COIN"};
        test.setForceDice(forcedRoll);
        int[] saving = {5,6,7};
        test.save(saving);
        test.reroll();
        String[] forcedReroll = {"PARROT","PARROT","PARROT","PARROT","PARROT","DIAMOND","DIAMOND","COIN"};
        int[] saving2 = {0,1,2,3,4};
        test.save(saving2);
        test.setForceDice(forcedRoll);
        String[] forcedReroll2 = {"PARROT","PARROT","PARROT","PARROT","PARROT","SKULL","COIN","PARROT"};
        test.setForceDice(forcedReroll2);
        test.updateScore();
        assertEquals(1100, test.getScore());

    }

    @Test
    void Test94(){
        //roll 2 skulls, 3 parrots, 3 coins   put 3 coins in chest
        //  then rerolls 3 parrots and get 2 diamonds 1 coin    put coin in chest (now 4)
        //   then reroll 2 diamonds and get 1 skull 1 coin     score for chest only = 400 + 200 = 600 AND report death
        Player test = new Player("Test");
        test.setCard("TREASURE_CHEST");
        test.roll();
        String[] forcedRoll = {"SKULL","SKULL","PARROT","PARROT","PARROT","COIN","COIN","COIN"};
        test.setForceDice(forcedRoll);
        int[] saving = {5,6,7};
        test.save(saving);
        test.reroll();
        String[] forcedReroll = {"SKULL","SKULL","DIAMOND","DIAMOND","COIN","COIN","COIN","COIN"};
        test.setForceDice(forcedReroll);
        int[] saving2 = {4,5,6,7};
        test.save(saving2);
        test.reroll();
        String[] forcedReroll2 = {"SKULL","SKULL","SKULL","COIN","COIN","COIN","COIN","COIN"};
        test.setForceDice(forcedReroll2);
        test.updateScore();
        assertEquals(600, test.getScore());
    }


    @Test
    void Test97(){
        //3 monkeys, 3 swords, 1 diamond, 1 parrot FC: coin   => SC 400  (ie no bonus)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","SWORD","SWORD","SWORD","DIAMOND","PARROT"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(400, test.getScore());
    }
    
    @Test
    void Test98(){
        //3 monkeys, 3 swords, 2 coins FC: captain   => SC (100+100+200+500)*2 =  1800
        Player test = new Player("Test");
        test.setCard("CAPTAIN");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","SWORD","SWORD","SWORD","COIN","COIN"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(1800, test.getScore());
    }

    @Test
    void Test99(){
        //3 monkeys, 4 swords, 1 diamond, FC: coin   => SC 1000  (ie 100++200+100+100+bonus)
        Player test = new Player("Test");
        test.setCard("GOLD");
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","SWORD","SWORD","SWORD","SWORD","COIN"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(1000, test.getScore());
    }

    @Test
    void Test102(){
        //FC: 2 sword sea battle, first  roll:  4 monkeys, 1 sword, 2 parrots and a coin
        //     then reroll 2 parrots and get 2nd coin and 2nd sword
        //     score is: 200 (coins) + 200 (monkeys) + 300 (swords of battle) + 500 (full chest) = 1200
        Player test = new Player("Test");
        test.setCard("SEA_BATTLE");
        test.setSwordsSea(2);
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","MONKEY","SWORD","PARROT","PARROT","COIN"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"MONKEY","MONKEY","MONKEY","MONKEY","SWORD","SWORD","COIN","COIN"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(1200, test.getScore());
    }

    @Test
    void Test103(){
        //FC: monkey business and roll 2 monkeys, 1 parrot, 2 coins, 3 diamonds   SC 1200
        Player test = new Player("Test");
        test.setCard("MONKEY_BUSINESS");
        test.setSwordsSea(2);
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","PARROT","COIN","COIN","DIAMOND","DIAMOND","DIAMOND"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(1200,test.getScore());
    }

    @Test
    void Test106(){
        //roll one skull and 7 swords with FC with two skulls => die
        Player test = new Player("Test");
        test.setCard("SKULL");
        test.setSkullFace(2);
        test.roll();
        String[] forcedRoll = {"SKULL","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(0,test.getScore());
    }

    @Test
    void Test107(){
        //roll 2 skulls and 6 swords with FC with 1 skull  => die
        Player test = new Player("Test");
        test.setCard("SKULL");
        test.setSkullFace(1);
        test.roll();
        String[] forcedRoll = {"SKULL","SKULL","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateScore();
        assertEquals(0,test.getScore());
    }

    @Test
    void Test109(){
        //roll 2 skulls  3(parrots/monkeys) with FC with two skulls: reroll 3 parrots get 2 skulls, 1 sword
        //  reroll sword and 3 monkeys, get 3 skulls and 1 sword, stop => -900 for other players (no negative score) & you score 0
        Player test = new Player("Test");
        Player test2 = new Player("Test2");
        Player test3 = new Player("Test3");
        test.setCard("SKULL");
        test.setSkullFace(2);
        test.roll();
        String[] forcedRoll = {"SKULL","SKULL","PARROT","PARROT","PARROT","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll = {"SKULL","SKULL","SKULL","SKULL","SWORD","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedReroll);
        test.reroll();
        String[] forcedReroll2 = {"SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","SWORD"};
        test.setForceDice(forcedReroll2);
        test.updateAlive();

        assertEquals(9,test.getSkullCount());
        test2.deductScore(test.getSkullCount());
        test3.deductScore(test.getSkullCount());
        assertEquals(0, test2.getScore());
        assertEquals(0, test3.getScore());
        test.setForceDice(forcedReroll2);
        test.updateScore();
        assertEquals(0,test.getScore());
    }
    @Test
    void Test110(){
        //roll 5 skulls, 3 monkeys with FC Captain, reroll 3 monkeys, get 2 skulls, 1 coin, stop => -1400 for other players
        Player test = new Player("Test");
        Player test2 = new Player("Test2");
        Player test3 = new Player("Test3");
        test.setCard("CAPTAIN");
        test.roll();
        String[] forcedRoll = {"SKULL","SKULL","SKULL","SKULL","SKULL","MONKEY","MONKEY","MONKEY"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll = {"SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","MONKEY"};
        test.setForceDice(forcedReroll);

        test.updateAlive();
        assertEquals(14,test.getSkullCount()); // 14 cause of captain
        test2.deductScore(test.getSkullCount());
        test3.deductScore(test.getSkullCount());
        assertEquals(0, test2.getScore());
        assertEquals(0, test3.getScore());
        test.updateScore();
        assertEquals(0, test.getScore());

    }
    @Test
    void Test111(){
        //roll 3 skulls and 5 swords with FC with two skulls:
        // reroll 5 swords, get 5 coins, must stop  => -500 for other players
        Player test = new Player("Test");
        Player test2 = new Player("Test2");
        Player test3 = new Player("Test3");
        test.setCard("SKULL");
        test.setSkullFace(2);
        test.roll();
        String[] forcedRoll = {"SKULL","SKULL","SKULL","SWORD","SWORD","SWORD","SWORD","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.reroll();
        String[] forcedReroll = {"SKULL","SKULL","SKULL","COIN","COIN","COIN","COIN","COIN"};
        test.setForceDice(forcedReroll);

        test.updateAlive();
        assertEquals(5,test.getSkullCount());
        test2.deductScore(test.getSkullCount());
        test3.deductScore(test.getSkullCount());
        assertEquals(0, test2.getScore());
        assertEquals(0, test3.getScore());
        test.updateScore();
        assertEquals(0, test.getScore());

    }

    @Test
    void Test114(){
        //FC 2 swords, roll 4 monkeys, 3 skulls & 1 sword and die   => die and lose 300 points
        Player test = new Player("Test");
        test.setCard("SEA_BATTLE");
        test.setSwordsSea(2);
        test.roll();
        String[] forcedRoll = {"MONKEY","MONKEY","MONKEY","MONKEY","SKULL","SKULL","SKULL","SWORD"};
        test.setForceDice(forcedRoll);
        test.updateAlive();
        test.updateScore();
        assertEquals(0, test.getScore());
    }

    @Test
    void Test115(){
        //FC 3 swords, have 2 swords, 2 skulls and 4 parrots, reroll 4 parrots, get 4 skulls=> die and lose 500 points
        Player test = new Player("Test");
        test.setCard("SEA_BATTLE");
        test.setSwordsSea(3);
        test.roll();
        String[] forcedRoll = {"SWORD","SWORD","SKULL","SKULL","PARROT","PARROT","PARROT","PARROT"};
        test.setForceDice(forcedRoll);
        test.reroll();
        String[] forcedReroll = {"SWORD","SWORD","SKULL","SKULL","SKULL","SKULL","SKULL","SKULL"};
        test.setForceDice(forcedReroll);
        test.updateScore();
        assertEquals(0, test.getScore());
    }

}