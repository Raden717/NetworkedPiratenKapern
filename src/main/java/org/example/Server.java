package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

// Server class
public class Server
{

    //Client vector to keep track of the players and allow listening/sending
    static Vector<ClientHandler> players = new Vector<>();

    //Player count (should max to 3)
    static int playerCounts = 0;


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
