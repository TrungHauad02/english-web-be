package com.englishweb.english_web_be.service;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;

public interface TextToSpeechService {

    InputStreamResource convertTextToSpeech(String text, String voice) throws IOException, InterruptedException;

    String getAvailableVoices() throws IOException, InterruptedException;
}
