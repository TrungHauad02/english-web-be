package com.englishweb.english_web_be.service;

public interface CommentListeningQuestionService {
    String commentListeningQuestion(String questionContent, String listeningTranscript, String[] answers, String userAnswer);
}
