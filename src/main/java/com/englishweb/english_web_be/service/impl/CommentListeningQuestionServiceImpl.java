package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.CommentResponse;
import com.englishweb.english_web_be.service.CommentListeningQuestionService;
import com.englishweb.english_web_be.service.GeminiClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
            promptBuilder.append("Analyze the listening transcript, question, and answers below, then provide a 30-word comment on the user's response:\n");
            promptBuilder.append("Listening Transcript: ").append(listeningTranscript).append("\n");
            promptBuilder.append("Question Content: ").append(questionContent).append("\n");
            promptBuilder.append("Answers:\n");
            for (int i = 0; i < answers.length; i++) {
                promptBuilder.append(i + 1).append(". ").append(answers[i]).append("\n");
            }
            promptBuilder.append("User's answer: ").append(userAnswer).append("\n");
            promptBuilder.append("Provide a concise comment on correctness, confusion points, and suggestions for improvement, limited to 30 words.");

            String prompt = promptBuilder.toString();
            log.info("Generated 30-word prompt for listening analysis: {}", prompt);
            String response = geminiClientService.generateText(prompt);
            log.info("Response received from Gemini: {}", response);

            String comment = response.trim();
            log.info("Generated concise comment: {}", comment);

            return CommentResponse.builder()
                    .comment(comment)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating concise listening comment", e);
        }
    }
}
