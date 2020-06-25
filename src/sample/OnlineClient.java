package sample;

import java.io.*;
import java.net.*;

public class OnlineClient {

    static PrintWriter pWrite;

    public static void connectToServer(String ipDomain, int port) {

        BufferedReader receiveRead;

        try {
            Socket sock = new Socket(ipDomain, port);
            pWrite = new PrintWriter(sock.getOutputStream(), true);
            receiveRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        System.out.println("Connected to " + ipDomain + ":" + port);


        Thread t = new Thread(() -> {
            while (true) {
                try {
                    String receiveMessage;
                    if ((receiveMessage = receiveRead.readLine()) != null) {
                        System.out.println(receiveMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public static void sendMessage(String message) {
        pWrite.println(message);
        pWrite.flush();
    }
}
