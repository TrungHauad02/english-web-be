package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.SpeechToTextResponse;

import java.io.IOException;

public interface SpeechToTextService {

    SpeechToTextResponse speechToText(String audioUrl, int channel_counts) throws IOException;
}
