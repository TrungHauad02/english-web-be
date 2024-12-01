package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.ScoreCommentResponse;

import java.math.BigDecimal;

public interface ScoreTestWritingService {
    ScoreCommentResponse scoreWriting(String text, String topic, String maxScore);
}
