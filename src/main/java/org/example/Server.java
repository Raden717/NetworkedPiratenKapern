package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

// Server class
public class Server
{

    //Client vector to keep track of the players and allow listening/sending
    static Vector<Player> playerInfo = new Vector<>();
    static Vector<ClientHandler> players = new Vector<>();

    //Player count (should max to 3)
    static int playerCounts = 0;

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
        this.isDoneTurn=false;
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
                            String MessageToPlayer = "Type roll to begin your turn: " ;
                            mc.send.writeUTF(MessageToPlayer);

                        } else {
                            mc.send.writeUTF("Game has started, waiting for your turn: ");
                        }
                    }
                } else if(received.equals("roll") || receivedArray[receivedArray.length-1].equals("reroll")){
                    for(ClientHandler mc : Server.players){
                        if(mc.name.equals(this.name)){
                            for(Player p: Server.playerInfo){
                                if(p.getName().equals(this.name)) {
                                    if (received.equals("roll")){
                                        p.rollCard();
                                        p.roll();
                                    } else {
                                        if(receivedArray.length > 1) {
                                            int[] keeping = new int[receivedArray.length - 2];
                                            for(int i = 0; i < receivedArray.length-1; i++){
                                                keeping[i] = Integer.valueOf(receivedArray[i]);
                                            }
                                            p.keep(keeping);
                                        }

                                        p.reroll();
                                    }
                                    p.updateAlive();
                                    mc.send.writeUTF("Here is your dice");
                                    for (int i = 0; i < 8; i++) {
                                        mc.send.writeUTF(String.valueOf(i) + "." + p.getRolled()[i]);
                                    }
                                    mc.send.writeUTF("Here is your card: " + p.getCard());
                                    if(p.getAlive()){
                                        mc.send.writeUTF("If you would like to reroll, state the cards you would" +
                                                "like to keep and then type reroll after ex. '1 2 3 4 reroll' or if you would like to end turn type 'Finish' " );
                                    } else {
                                        if(p.getislandofSkulls()){

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
                            if(p.getAlive()){
                                p.updateScore();
                            }
                        }
                    }
                    String scoreboard = "Current Scores\n";
                    for(Player p: Server.playerInfo){
                        scoreboard = scoreboard + p.getName() + ": " + String.valueOf(p.getScore()) + "\n";
                    }
                    for (ClientHandler mc : Server.players)
                    {
                        mc.send.writeUTF(scoreboard);
                        System.out.println(nextPlayer);
                        System.out.println(mc.name);
                        if(mc.name.equals(nextPlayer)){
                            mc.send.writeUTF("It is now your turn, type roll to start");
                        } else {
                            mc.send.writeUTF("Waiting for your turn");
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
