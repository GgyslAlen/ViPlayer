package com.atmos.server;

import com.atmos.player.Player;
import com.atmos.player.PlayerImpl;
import com.atmos.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public class SocketClient extends WebSocketClient {

    private static final Player player = new PlayerImpl();

    private static final String projectDir = System.getProperty("user.dir");

    private static final Logger log = LogManager.getLogger(SocketClient.class);

    public SocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        log.info("Server connection open: " + handshakedata);
    }

    @Override
    public void onMessage(String message) {
        log.info("Got message: " + message);
        byte[] video = Base64.getDecoder().decode(message);
        log.info("Got video from server: {} size", video.length);
        String saveFileTo = projectDir + "\\playlist\\" +  UUID.randomUUID() + ".mp4";
        try {
            FileUtils.saveFile(video, saveFileTo);
            player.play(saveFileTo, 512, 267);
        } finally {
            //wait for pygame close
            //try {
            //    Thread.sleep(1000L);
            //} catch (InterruptedException e) {
            //    log.error(e);
            //}
            //if (!FileUtils.delete(saveFileTo))
                //log.info("Can't delete temporary file");
        }
    }

    @Override
    public void onMessage(ByteBuffer message) {
        byte[] video = message.array();

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Connection closed: " + code + " " + reason);
    }

    @Override
    public void onError(Exception ex) {
        log.error(ex);
    }

}

