package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglePlayerTests {

    @Test
    void Test44(){
        Player test = new Player("Test");

        test.setSkulls(3); //Forcing skull count before the roll so the "roll" contains 3 skulls immediately
        test.roll();
        assertEquals(false,test.getAlive());
    }

}