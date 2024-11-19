package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.CommentResponse;

public interface CommentReadingQuestionService {
    CommentResponse commentReadingQuestion(String questionContent, String readingContent, String[] answers, String userAnswer);
}
