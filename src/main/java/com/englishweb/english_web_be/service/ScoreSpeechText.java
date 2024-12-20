package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.ScoreSpeechTextResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScoreSpeechText {

    ScoreSpeechTextResponse scoreSpeechTextAndRealText(String speechText, String realText) throws JsonProcessingException;
}
