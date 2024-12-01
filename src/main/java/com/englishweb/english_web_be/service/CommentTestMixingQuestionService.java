package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.CommentResponse;

public interface CommentTestMixingQuestionService {
     CommentResponse commentTestMixingQuestion(String question, String[] answers, String userAnswer);
}
