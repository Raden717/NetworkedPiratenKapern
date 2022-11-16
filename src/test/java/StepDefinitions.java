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

    @Given("Player2 rolls {String[]} dice and Card {String} ")
    public void forcedRollP2(String[] rolls, String card) {
        test2 = new Player("Test");
        test2.roll();
        test2.setForceDice(rolls);
        test2.setCard(card);
    }

    @When("Player2 decides to end turn")
    public void endTurnP2(){
        test2.updateAlive();
        test2.updateScore();
    }

    @Given("Player3 rolls {String[]} dice and Card {String} ")
    public void forcedRollP3(String[] rolls, String card) {
        test3 = new Player("Test");
        test3.roll();
        test3.setForceDice(rolls);
        test3.setCard(card);
    }

    @When("Player3 decides to end turn")
    public void endTurnP3(){
        test3.updateAlive();
        test3.updateScore();
    }

    @Then("Player1 should have score {int}, Player2 should have score {int}, Player3 should have score {int} ")
    public void endScore(int score1, int score2, int score3){
        assertEquals(score1, test.getScore());
        assertEquals(score2, test2.getScore());
        assertEquals(score3, test3.getScore());
    }



}
