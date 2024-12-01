package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.response.CommentResponse;

public interface CommentTestListeningQuestionService {
    CommentResponse commentTestListeningQuestion(String questionContent, String listeningTranscript, String[] answers, String userAnswer);
}
