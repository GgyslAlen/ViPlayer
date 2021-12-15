package com.atmos;

import com.atmos.player.Player;
import com.atmos.player.PlayerImpl;
import com.atmos.server.SocketClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        SocketClient socketClient = new SocketClient(new URI("ws://192.168.1.122:8080/endpoint"));
        socketClient.connect();
        new Thread(() -> {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            socketClient.send("GIVE MY VIDEO!!");
        }).start();
    }

}
