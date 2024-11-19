package com.englishweb.english_web_be.service;

public interface CommentReadingQuestionService {
    String commentReadingQuestion(String questionContent, String readingContent, String[] answers, String userAnswer);
}
