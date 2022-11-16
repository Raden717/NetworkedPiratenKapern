import io.cucumber.java.en.*;
import org.example.*;
import static org.junit.jupiter.api.Assertions.*;



public class StepDefinitions {
    Player test;
    Player test2;
    Player test3;

    int pointThreshold = 3000;

    @Given("Player1 rolls {String[]} dice and Card {String} ")
    public void forcedRollP1(String[] rolls, String card) {
        pointThreshold = 3000;
        test = new Player("Test");
        test.roll();
        test.setForceDice(rolls);
        test.setCard(card);
    }

    @And("Player1 decides to end turn")
    public void endTurnP1(){
        test.updateAlive();
        test.updateScore();
        test.checkIfWon(pointThreshold);
        if(test.getScore() > pointThreshold){
            pointThreshold = test.getScore();
        }
    }

    @And("Player2 rolls {String[]} dice and Card {String} ")
    public void forcedRollP2(String[] rolls, String card) {
        test2 = new Player("Test");
        test2.roll();
        test2.setForceDice(rolls);
        test2.setCard(card);
    }

    @And("Player2 decides to end turn")
    public void endTurnP2(){
        test2.updateAlive();
        test2.updateScore();
        test.checkIfWon(pointThreshold);
        if(test3.getScore() > pointThreshold){
            pointThreshold = test3.getScore();
        }
    }

    @And("Player3 rolls {String[]} dice and Card {String} ")
    public void forcedRollP3(String[] rolls, String card) {
        test3 = new Player("Test");
        test3.roll();
        test3.setForceDice(rolls);
        test3.setCard(card);
    }

    @And("Player3 decides to end turn")
    public void endTurnP3(){
        test3.updateAlive();
        test3.updateScore();
        test3.checkIfWon(pointThreshold);
        if(test3.getScore() > pointThreshold){
            pointThreshold = test3.getScore();
        }
    }

    @Then("Player1 should have score {int}, Player2 should have score {int}, Player3 should have score {int} ")
    public void endScore(int score1, int score2, int score3){
        assertEquals(score1, test.getScore());
        assertEquals(score2, test2.getScore());
        assertEquals(score3, test3.getScore());
    }

    @Then("Player1 should have score {int}, Player2 should have score {int}, Player3 should have score {int} and Player1 should have won {boolean}")
    public void endScore(int score1, int score2, int score3, boolean won){
        test.checkIfWon(pointThreshold);
        assertEquals(score1, test.getScore());
        assertEquals(score2, test2.getScore());
        assertEquals(score3, test3.getScore());
        assertEquals(won, test.getWinner());

    }





}
