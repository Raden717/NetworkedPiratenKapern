package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglePlayerTests {

    @Test
    void Test44(){
        Player test = new Player("Test");

        int prevScore = Player.getScore();

        Player.roll();
        Player.setSkulls(3);
        Player.roll();
        Player.updateScore();

        assertEquals(prevScore, Player.getScore());
    }

}