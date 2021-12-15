package com.atmos.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileUtils {

    private static final Logger log = LogManager.getLogger(FileUtils.class);

    public static void saveFile(byte[] source, String path) {
        try {
            OutputStream os = new FileOutputStream(path);
            os.write(source);
            os.close();
        } catch (Exception e) {
            log.error(e);
        }
    }

    public static boolean delete(String path) {
        File file = new File(path);
        return file.delete();
    }

}
