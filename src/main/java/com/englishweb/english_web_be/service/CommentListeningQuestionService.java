package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.CommentResponse;

public interface CommentListeningQuestionService {
    CommentResponse commentListeningQuestion(String questionContent, String listeningTranscript, String[] answers, String userAnswer);
}
