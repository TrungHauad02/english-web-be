package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.ScoreWritingResponse;

public interface ScoreWritingService {
    ScoreWritingResponse scoreWriting(String text, String topic);
}
