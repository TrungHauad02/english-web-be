package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.CommentResponse;
import com.englishweb.english_web_be.service.CommentTestReadingQuestionService;
import com.englishweb.english_web_be.service.GeminiClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentTestReadingQuestionServiceImpl implements CommentTestReadingQuestionService {
    private final GeminiClientService geminiClientService;

    public CommentTestReadingQuestionServiceImpl(GeminiClientService geminiClientService) {
        this.geminiClientService = geminiClientService;
    }

    public CommentResponse commentTestReadingQuestion(String questionContent, String readingContent, String[] answers, String userAnswer) {
        try {
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append("Analyze the following reading content, question, and answers, then provide a 30-word comment on the user's answer:\n");
            promptBuilder.append("Reading Content: ").append(readingContent).append("\n");
            promptBuilder.append("Question Content: ").append(questionContent).append("\n");
            promptBuilder.append("Answers:\n");
            for (int i = 0; i < answers.length; i++) {
                promptBuilder.append(i + 1).append(". ").append(answers[i]).append("\n");
            }
            promptBuilder.append("User's answer: ").append(userAnswer).append("\n");
            promptBuilder.append("Give a concise comment on the correctness, potential confusion points, and improvement suggestions, limited to 30 words.");

            String prompt = promptBuilder.toString();
            log.info("Generated 30-word prompt for reading analysis: {}", prompt);
            String response = geminiClientService.generateText(prompt);
            log.info("Response received from Gemini: {}", response);

            String comment = response.trim();
            log.info("Generated concise comment: {}", comment);

            return CommentResponse.builder()
                    .comment(comment)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating concise reading comment", e);
        }
    }
}
