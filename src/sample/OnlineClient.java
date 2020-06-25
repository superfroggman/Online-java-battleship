package sample;

import java.io.*;
import java.net.*;

public class OnlineClient {

    static PrintWriter pWrite;
    public static BufferedReader receiveRead;

    public static void connectToServer(String ipDomain, int port) {

        try {
            Socket sock = new Socket(ipDomain, port);
            pWrite = new PrintWriter(sock.getOutputStream(), true);
            receiveRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        System.out.println("Connected to " + ipDomain + ":" + port);

    }

    public static void sendMessage(String message) {
        pWrite.println(message);
        pWrite.flush();
    }
}
