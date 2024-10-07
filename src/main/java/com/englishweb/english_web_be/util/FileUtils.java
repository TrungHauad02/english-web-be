package com.englishweb.english_web_be.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static final String AUDIO_DIRECTORY = "src/main/resources/audio/";

    public static String writeSoundBytesToFile(byte[] soundBytes) {
        File audioDir = new File(AUDIO_DIRECTORY);
        if (!audioDir.exists()) {
            audioDir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + ".mp3";
        File audioFile = new File(audioDir, fileName);

        try (FileOutputStream fos = new FileOutputStream(audioFile)) {
            fos.write(soundBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
