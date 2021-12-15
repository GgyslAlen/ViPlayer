package com.atmos.player;

import lombok.Value;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class PlayerImpl implements Player {

    private static final String projectPath = System.getProperty("user.dir");

    private static final Logger log = LogManager.getLogger(Player.class);

    @Override
    public void play(String videoPath, int width, int height) {
        log.info(videoPath);
        String playScriptPath = projectPath + "/src/main/python/player/player.py";
        log.info(playScriptPath);
        Long currTime = new Date().getTime();
        try {
            Process p = Runtime.getRuntime().exec("python3 " + playScriptPath + " " + videoPath + " " + width + " " + height);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            log.info("player result: {}", ret);
        } catch (IOException e) {
            log.error(e);
        } finally {
            Long futureTime = new Date().getTime();
            log.info("mSec left: {}", futureTime - currTime);
        }
    }


}
