package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.ScoreResponse;

public interface ScoreWritingService {
    ScoreResponse scoreWriting(String text, String topic);
}
