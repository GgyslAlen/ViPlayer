package com.atmos;

import com.atmos.player.Player;
import com.atmos.player.PlayerImpl;
import com.atmos.server.SocketClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        SocketClient socketClient = new SocketClient(new URI("ws://192.168.1.122:8080/endpoint"));
        socketClient.connect();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String mess = sc.next();
            socketClient.send(mess);
        }
    }

}
