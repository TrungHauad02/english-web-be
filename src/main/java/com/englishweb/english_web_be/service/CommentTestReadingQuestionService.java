package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.CommentResponse;

public interface CommentTestReadingQuestionService {
    CommentResponse commentTestReadingQuestion(String questionContent, String readingContent, String[] answers, String userAnswer);
}
