package StepDefinitions;

import io.cucumber.java.en.*;
import org.example.Player;

import static org.junit.jupiter.api.Assertions.*;



public class StepDefinitions {
    Player test;
    Player test2;
    Player test3;

    int pointThreshold = 3000;

    @Given("PlayerOne rolls {string},{string},{string},{string},{string},{string},{string},{string} dice and Card {string} with face value {int}")
    public void forcedRollSingle(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8, String card, int numFace) {
        String[] rolls = {s1,s2,s3,s4,s5,s6,s7,s8};
        pointThreshold = 3000;
        test = new Player("Test");
        test2 = new Player("Test2");
        test3 = new Player("Test3");
        test.roll();
        test.setForceDice(rolls);
        test.setCard(card);
        if(card.equals("SEA_BATTLE")){
            test.setSwordsSea(numFace);
        } else if (card.equals("SKULL")){
            test.setSkullFace(numFace);
        }
    }

    @And("PlayerTwo rolls {string},{string},{string},{string},{string},{string},{string},{string} dice and Card {string} with face value {int}")
    public void forcedRollP2(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8, String card, int faceValue) {
        String[] rolls = {s1,s2,s3,s4,s5,s6,s7,s8};

        test2 = new Player("Test2");

        test2.roll();
        test2.setForceDice(rolls);
        test2.setCard(card);
        if(card.equals("SEA_BATTLE")){
            test2.setSwordsSea(faceValue);
        } else if (card.equals("SKULL")){
            test2.setSkullFace(faceValue);
        }
    }

    @And("PlayerThree rolls {string},{string},{string},{string},{string},{string},{string},{string} dice and Card {string} with a face value {int}")
    public void forcedRollP3(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8, String card, int faceValue) {
        String[] rolls = {s1,s2,s3,s4,s5,s6,s7,s8};

        test3 = new Player("Test3");

        test3.roll();
        test3.setForceDice(rolls);
        test3.setCard(card);
        if(card.equals("SEA_BATTLE")){
            test3.setSwordsSea(faceValue);
        } else if (card.equals("SKULL")){
            test3.setSkullFace(faceValue);
        }
    }

    @And("PlayerOne rolls again {string},{string},{string},{string},{string},{string},{string},{string} dice and Card {string} with a face value {int}")
    public void forcedRollP1(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8, String card, int faceValue) {
        String[] rolls = {s1,s2,s3,s4,s5,s6,s7,s8};

        test.roll();
        test.setForceDice(rolls);
        test.setCard(card);
        if(card.equals("SEA_BATTLE")){
            test.setSwordsSea(faceValue);
        } else if (card.equals("SKULL")){
            test.setSkullFace(faceValue);
        }
    }

    @And("PlayerOne rolls again {string},{string},{string},{string},{string},{string},{string},{string} dice")
    public void forcedRollAgain(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8) {
        String[] rolls = {s1,s2,s3,s4,s5,s6,s7,s8};

        test.reroll();
        test.setForceDice(rolls);
    }

    @And("Player saves 3 dice in treasure chest at position {int}, {int}, {int}")
    public void save3Dice(int n1, int n2, int n3){
        int[] saving = {n1,n2,n3};
        test.save(saving);
    }

    @And("Player clears chest and saves 4 dice in treasure chest, positions {int}, {int}, {int}, {int}")
    public void save5Dice(int n1, int n2, int n3, int n4){
        int[] saving = {n1,n2,n3,n4};
        test.save(saving);
    }

    @And("Player clears chest and saves 5 dice in treasure chest, positions {int}, {int}, {int}, {int}, {int}")
    public void save5Dice(int n1, int n2, int n3, int n4, int n5){
        int[] saving = {n1,n2,n3,n4,n5};
        test.save(saving);
    }


    @And("PlayerThree rolls {string},{string},{string},{string},{string},{string},{string},{string} dice and Card {string}")
    public void forcedRollP3(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8, String card) {
        String[] rolls = {s1,s2,s3,s4,s5,s6,s7,s8};

        test3.roll();
        test3.setForceDice(rolls);
        test3.setCard(card);
    }

    @And("PlayerOne decides to end turn")
    public void endTurnP1(){
        test.updateAlive();
        test.updateScore();
        test.checkIfWon(pointThreshold);
        if(test.getScore() > pointThreshold){
            pointThreshold = test.getScore();
        }
    }


    @And("PlayerTwo decides to end turn")
    public void endTurnP2(){
        test2.updateAlive();
        test2.updateScore();
        test.checkIfWon(pointThreshold);
        if(test2.getScore() > pointThreshold){
            pointThreshold = test2.getScore();
        }
    }


    @And("PlayerThree decides to end turn")
    public void endTurnP3(){
        test3.updateAlive();
        test3.updateScore();
        test3.checkIfWon(pointThreshold);
        if(test3.getScore() > pointThreshold){
            pointThreshold = test3.getScore();
        }
    }

    @And("PlayerOne dies")
    public void diesP1(){
        test.updateAlive();
        test.updateScore();
        test.checkIfWon(pointThreshold);
        if(test.getScore() > pointThreshold){
            pointThreshold = test.getScore();
        }
    }


    @And("PlayerTwo dies")
    public void diesP2(){
        test2.updateAlive();
        test2.updateScore();
        test.checkIfWon(pointThreshold);
        if(test2.getScore() > pointThreshold){
            pointThreshold = test2.getScore();
        }
    }


    @And("PlayerThree dies")
    public void diesP3(){
        test3.updateAlive();
        test3.updateScore();
        test3.checkIfWon(pointThreshold);
        if(test3.getScore() > pointThreshold){
            pointThreshold = test3.getScore();
        }
    }


    @Then("PlayerOne should have score {int}, PlayerTwo should have score {int}, PlayerThree should have score {int} and PlayerOne should have won true")
    public void endScore(int score1, int score2, int score3){
        assertEquals(score1, test.getScore());
        assertEquals(score2, test2.getScore());
        assertEquals(score3, test3.getScore());
        assertEquals(true, test.getWinner());
        assertEquals(false, test2.getWinner());
        assertEquals(false, test3.getWinner());
        System.out.println("Is Player one winner " +  test.getWinner());
        System.out.println("Is Player two winner " + test2.getWinner());
        System.out.println("Is Player three winner " + test3.getWinner());


    }

    @Then("PlayerOne should have score {int}")
    public void singleEndScore(int score1){
        assertEquals(score1, test.getScore());
    }

    @Then("PlayerOne should have score {int} and lost {int} from seaBattle")
    public void singleEndScore(int score1, int scoreLost){
        assertEquals(score1, test.getScore());
        assertEquals(scoreLost, test.getSeaBattleDeduction());
    }

    @Then("PlayerOne should have an updated score of {int} and should be dead")
    public void singleEndScoreDeath(int score1){
        assertEquals(true, test.getAlive());
        assertEquals(score1, test.getScore());
    }


    @When("The set of turns end")
    public void theSetOfTurnsEnd() {
        test.checkIfWon(pointThreshold);
        test2.checkIfWon(pointThreshold);
        test3.checkIfWon(pointThreshold);

    }

    @And("PlayerTwo rerolls to have {string},{string},{string},{string},{string},{string},{string},{string}")
    public void playertwoRerollsToHave(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        String[] rolls = {s1,s2,s3,s4,s5,s6,s7,s8};
        System.out.println("Sorceress uses left " + test2.getSorceressUse());
        test2.reroll();
        test2.setForceDice(rolls);
        System.out.println("Sorceress uses left " + test2.getSorceressUse());

    }

    @And("Scores are deducted by {int} due to island of skulls by player {string}")
    public void scoresAreDeductedByDueToIslandOfSkulls(int arg0, String playerName) {
        test.deductScore(arg0);
        test2.deductScore(arg0);
        test3.deductScore(arg0);

    }

    @When("checking scores")
    public void checkingScores() {
        test.checkIfWon(pointThreshold);
        test2.checkIfWon(pointThreshold);
        test3.checkIfWon(pointThreshold);
    }

    @Then("PlayerOne should have score {int}, PlayerTwo should have score {int}, PlayerThree should have score {int}")
    public void playeroneShouldHaveScoreIntPlayerTwoShouldHaveScoreIntPlayerThreeShouldHaveScoreInt(int score1, int score2, int score3) {
        assertEquals(score1, test.getScore());
        assertEquals(score2, test2.getScore());
        assertEquals(score3, test3.getScore());

    }


    @Then("PlayerOne should have score {int} left")
    public void playeroneShouldHaveScoreNumScoreLeft(int score) {
        assertEquals(score, test.getScore());
    }

}
