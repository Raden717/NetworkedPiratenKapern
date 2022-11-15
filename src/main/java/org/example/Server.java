package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

// Server class for the program
public class Server
{

    //Client vector to keep track of the players and allow listening/sending
    static Vector<Player> playerInfo = new Vector<>();
    static Vector<ClientHandler> players = new Vector<>();

    //Player count (should max to 3)
    static int playerCounts = 0;

    static Player winner;

    static boolean gameEnd;

    static int pointThreshold = 3000;
    static boolean gameStart = false;


    public static void main(String[] args) throws IOException
    {

        ServerSocket ss = new ServerSocket(7777);

        Socket s;
        while (true)
        {
            s = ss.accept();

            System.out.println("New player request and accepted: " + s);

            //Setting up the streams
            DataInputStream recStream = new DataInputStream(s.getInputStream());
            DataOutputStream sendStream = new DataOutputStream(s.getOutputStream());

            //New handler for each request
            ClientHandler mtch = new ClientHandler(s,"Player " + (playerCounts+1), recStream, sendStream);

            //New Thread for each request
            Thread t = new Thread(mtch);
            Player newPlayer = new Player("Player " + (playerCounts+1));
            playerInfo.add(newPlayer);
            System.out.println("Player " + (playerCounts + 1) + " has been added to the game" );

            // add this client to active clients list
            players.add(mtch);

            // start the thread.
            t.start();

            // increment i for new client.
            // i is used for naming only, and can be replaced
            // by any naming scheme


            playerCounts++;

        }
    }
}

// ClientHandler class
class ClientHandler implements Runnable
{
    Scanner scn = new Scanner(System.in);
    final String name;
    final DataInputStream rec;
    final DataOutputStream send;
    Socket s;

    boolean isDoneTurn;

    // constructor
    public ClientHandler(Socket s, String name,
                         DataInputStream rec, DataOutputStream send) {
        this.rec = rec;
        this.send = send;
        this.name = name;
        this.s = s;
        this.isDoneTurn=true;
    }

    @Override
    public synchronized void run() {

        String received;
        while (true)
        {
            try
            {
                // receive the string
                received = rec.readUTF();
                if(received.equals("end")){
                    this.s.close();
                    break;
                }

                System.out.println(received);
                String[] receivedArray = received.split(" ");

                if(received.toUpperCase().equals("START") && (!Server.gameStart)){
                    Server.gameStart = true;
                    for(ClientHandler mc : Server.players){

                        if(mc.name.equals("Player 1")){
                            mc.isDoneTurn = false;
                            String MessageToPlayer = "Type roll to begin your turn: " ;
                            mc.send.writeUTF(MessageToPlayer);

                        } else {
                            mc.send.writeUTF("Game has started, waiting for your turn: ");
                        }
                    }
                }

                for(ClientHandler mc : Server.players){
                    if (this.name.equals(mc.name)){
                        if(mc.isDoneTurn){
                            mc.send.writeUTF("It is not your turn yet, wait until your turn.");
                            received = "ERROR";
                        }
                    }
                }
                if(receivedArray[receivedArray.length-1].equals("save")){
                    int[] saving = new int[receivedArray.length-1];
                    for(int i = 0; i < receivedArray.length-1; i++){
                        saving[i] = Integer.valueOf(receivedArray[i]);
                    }
                    for(ClientHandler mc : Server.players) {
                        if (mc.name.equals(this.name)) {
                            for (Player p : Server.playerInfo) {
                                if (p.getName().equals(this.name)) {
                                    if(p.getCard().equals("TREASURE_CHEST")){
                                        p.save(saving);
                                        mc.send.writeUTF("You have saved your cards");
                                        mc.send.writeUTF("If you would like to reroll, state the cards you would " +
                                                "like to keep and then type reroll after ex. '1 2 3 4 reroll' or just 'reroll', if you would like to end turn type 'Finish' " );
                                    }
                                }
                            }
                        }
                    }
                }

                if(received.equals("roll") || receivedArray[receivedArray.length-1].equals("reroll")){
                    for(ClientHandler mc : Server.players){
                        if(mc.name.equals(this.name)){
                            for(Player p: Server.playerInfo){
                                if(p.getName().equals(this.name)) {
                                    if (received.equals("roll")){
                                        p.rollCard();
                                        p.roll();
                                    } else {
                                        if(receivedArray.length > 1) {
                                            int[] keeping = new int[receivedArray.length - 1];
                                            for(int i = 0; i < receivedArray.length-1; i++){
                                                keeping[i] = Integer.valueOf(receivedArray[i]);
                                            }
                                            p.keep(keeping);
                                        }

                                        p.reroll();
                                    }
                                    mc.send.writeUTF("Here is your dice");
                                    for (int i = 0; i < 8; i++) {
                                        mc.send.writeUTF(String.valueOf(i) + "." + p.getRolled()[i]);
                                    }
                                    if(p.getCard().equals("SEA_BATTLE")){
                                        mc.send.writeUTF("Here is your card: " + p.getCard() +" "+ p.getswordBattleReq());
                                    } else if (p.getCard().equals("SKULL")) {
                                        mc.send.writeUTF("Here is your card: " + p.getCard() + " " + p.getSkullFace());
                                    } else if (p.getCard().equals("SORCERESS")){
                                        mc.send.writeUTF("Here is your card: " + p.getCard() + " Uses left: " + p.getSorceressUse());
                                    } else {
                                        mc.send.writeUTF("Here is your card: " + p.getCard());
                                    }
                                    p.updateAlive();

                                    if(p.getAlive()){
                                        if(p.getCard().equals("TREASURE_CHEST")){
                                            mc.send.writeUTF("You have a treasure chest, if you'd like to save type what you'd like" +
                                                    "to save with this format '1 2 3 4 save' '");

                                        }
                                        mc.send.writeUTF("If you would like to reroll, state the cards you would " +
                                                "like to keep and then type reroll after ex. '1 2 3 4 reroll' or just 'reroll', if you would like to end turn type 'Finish' " );
                                    } else {
                                        if(p.getislandofSkulls()){
                                            mc.send.writeUTF("You are now on the island of skulls, we will rerolling until you do not roll a skull");
                                            int skullCount = p.getSkullCount();
                                            while(true){
                                                p.reroll();
                                                mc.send.writeUTF("Here is your dice");
                                                for (int i = 0; i < 8; i++) {
                                                    mc.send.writeUTF(String.valueOf(i) + "." + p.getRolled()[i]);
                                                }
                                                if(p.getSkullCount() == skullCount){
                                                    break;
                                                } else {
                                                    skullCount = p.getSkullCount();
                                                }
                                            }
                                            mc.send.writeUTF("Your turn has no ended and you are deducting point sfrom the other players, type Finish to end your turn");
                                            for(Player newP : Server.playerInfo){
                                                if(this.name != newP.getName()){
                                                    newP.deductScore(skullCount);
                                                }
                                            }
                                            for(ClientHandler c : Server.players){
                                                if(this.name != c.name){
                                                    c.send.writeUTF("You have lost due to island of skulls" + String.valueOf(skullCount*100));
                                                }
                                            }

                                        } else {

                                            mc.send.writeUTF("You have died, end turn by typing Finish");
                                        }
                                    }

                                }
                            }
                        }
                    }
                } else if (received.equals("Finish")){
                    String nextPlayer;
                    String currPlayer = String.valueOf(this.name.charAt(this.name.length() - 1));
                    int nextPlayerInt = Integer.valueOf(currPlayer) + 1;
                    if(nextPlayerInt == 4){
                        nextPlayerInt = 1;
                    }
                    nextPlayer = "Player " + Integer.toString(nextPlayerInt);
                    for(Player p: Server.playerInfo){
                        if(this.name.equals(p.getName())){
                            if(p.getCard().equals("SEA_BATTLE")){
                                for (ClientHandler mc : Server.players){
                                    if(p.getName().equals(mc.name)){
                                        mc.send.writeUTF("You got " + String.valueOf(p.getSeaBattleDeduction()) +" from sea battle");
                                    }
                                }

                            }
                            p.updateScore();
                        }
                    }
                    String scoreboard = "Current Scores\n";
                    for(Player p: Server.playerInfo){
                        if(p.getScore() >= Server.pointThreshold){
                            Player prevWinner = Server.winner;
                            Server.winner = p;
                            Server.pointThreshold = p.getScore();
                            if(prevWinner == Server.winner){
                                Server.gameEnd = true;
                            }
                        }
                        scoreboard = scoreboard + p.getName() + ": " + String.valueOf(p.getScore()) + "\n";
                    }
                    for (ClientHandler mc : Server.players)
                    {
                        mc.send.writeUTF(scoreboard);
                        System.out.println(nextPlayer);
                        System.out.println(mc.name);
                        if(!Server.gameEnd){
                            if(mc.name.equals(nextPlayer)){
                                mc.isDoneTurn = false;
                                mc.send.writeUTF("It is now your turn, type roll to start");
                            } else {
                                mc.isDoneTurn = true;
                                mc.send.writeUTF("Waiting for your turn");
                            }
                        } else {
                            if(mc.name.equals(Server.winner.getName())){
                                mc.send.writeUTF("You've won!");
                            } else {
                                mc.send.writeUTF("You've lost!");

                            }
                        }

                    }
                }




            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.rec.close();
            this.send.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
