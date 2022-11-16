import io.cucumber.java.en.*;
import org.example.*;
import static org.junit.jupiter.api.Assertions.*;



public class StepDefinitions {
    Player test;
    Player test2;
    Player test3;

    @Given("Player1 rolls {String[]} dice and Card {String} ")
    public void forcedRollP1(String[] rolls, String card) {
        test = new Player("Test");
        test.roll();
        test.setForceDice(rolls);
        test.setCard(card);
    }

    @When("Player1 decides to end turn")
    public void endTurnP1(){
        test.updateAlive();
        test.updateScore();
    }

    @Then("Player1 should have score {int}, Player2 should have score {int}, Player3 should have score {int} ")
    public void endScore(int score1, int score2, int score3){
        assertEquals(score1, test.getScore());
        assertEquals(score2, test2.getScore());
        assertEquals(score3, test3.getScore());
    }



}
