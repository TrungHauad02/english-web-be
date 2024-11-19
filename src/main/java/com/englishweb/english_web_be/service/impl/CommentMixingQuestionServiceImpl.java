package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.CommentResponse;
import com.englishweb.english_web_be.service.CommentMixingQuestionService;
import com.englishweb.english_web_be.service.GeminiClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentMixingQuestionServiceImpl implements CommentMixingQuestionService {
    private final GeminiClientService geminiClientService;

    public CommentMixingQuestionServiceImpl(GeminiClientService geminiClientService) {
        this.geminiClientService = geminiClientService;
    }

    public CommentResponse commentMixingQuestion(String question, String[] answers, String userAnswer) {
        try {
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append("Given the question and answers below, provide a 30-word evaluation on the user's response:\n");
            promptBuilder.append("Question: ").append(question).append("\n");
            promptBuilder.append("Answers:\n");
            for (int i = 0; i < answers.length; i++) {
                promptBuilder.append(i + 1).append(". ").append(answers[i]).append("\n");
            }
            promptBuilder.append("User's answer: ").append(userAnswer).append("\n");
            promptBuilder.append("Provide a concise comment on correctness, potential confusion points, and suggestions for improvement within 30 words.");

            String prompt = promptBuilder.toString();
            log.info("Generated 30-word prompt: {}", prompt);
            String response = geminiClientService.generateText(prompt);
            log.info("Response received from Gemini: {}", response);

            String comment = response.trim();
            log.info("Generated concise comment: {}", comment);

            return CommentResponse.builder()
                    .comment(comment)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating concise academic comment", e);
        }
    }
}
