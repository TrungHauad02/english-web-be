package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.ScoreCommentResponse;

public interface ScoreWritingService {
    ScoreCommentResponse scoreWriting(String text, String topic);
}
