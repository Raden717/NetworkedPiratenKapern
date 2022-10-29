package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    final static int ServerPort = 7777;

    public static void main(String args[]) throws UnknownHostException, IOException
    {
        Scanner scn = new Scanner(System.in);

        //Getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        //Setting up connection
        Socket s = new Socket(ip, ServerPort);

        //Setting up the streams
        DataInputStream recStream = new DataInputStream(s.getInputStream());
        DataOutputStream sendStream = new DataOutputStream(s.getOutputStream());

        //Sending thread
        Thread sendMessage = new Thread(new Runnable()
        {
            @Override
            public synchronized void run() {
                while (true) {
                    String msg = scn.nextLine();
                    try {
                        //Sending the message through to the stream
                        sendStream.writeUTF(msg);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //Reading thread
        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public synchronized void run() {

                while (true) {
                    try {
                        // read the message sent to this client
                        String msg = recStream.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });

        sendMessage.start();
        readMessage.start();

    }
}
