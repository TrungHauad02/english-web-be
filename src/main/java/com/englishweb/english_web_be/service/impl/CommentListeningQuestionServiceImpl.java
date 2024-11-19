package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.CommentResponse;
import com.englishweb.english_web_be.service.CommentListeningQuestionService;
import com.englishweb.english_web_be.service.GeminiClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class CommentListeningQuestionServiceImpl implements CommentListeningQuestionService {
    private final GeminiClientService geminiClientService;

    public CommentListeningQuestionServiceImpl(GeminiClientService geminiClientService) {
        this.geminiClientService = geminiClientService;
    }

    public CommentResponse commentListeningQuestion(String questionContent, String listeningTranscript, String[] answers, String userAnswer) {
        try {
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append("Given the following listening transcript, question content, and related answers, generate a detailed comment on the user's selected answer:\n");
            promptBuilder.append("Listening Transcript: ").append(listeningTranscript).append("\n");
            promptBuilder.append("Question Content: ").append(questionContent).append("\n");
            promptBuilder.append("Answers:\n");
            for (int i = 0; i < answers.length; i++) {
                promptBuilder.append(i + 1).append(". ").append(answers[i]).append("\n");
            }
            promptBuilder.append("User's answer: ").append(userAnswer).append("\n");
            promptBuilder.append("Provide a comprehensive comment on the user's answer, analyzing its correctness and offering suggestions for improvement. The response should be concise, no longer than 30 words.");

            String prompt = promptBuilder.toString();
            log.info("Send prompt: {}", prompt);
            String response = geminiClientService.generateText(prompt);
            log.info("Response from Gemini: {}", response);

            String[] parts = response.split("\n");
            String comment = String.join("\n", Arrays.copyOfRange(parts, 0, parts.length));
            log.info("Comment: {}", comment);

            return CommentResponse.builder()
                    .comment(comment)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
