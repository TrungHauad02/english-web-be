package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.ScoreResponse;
import com.englishweb.english_web_be.service.GeminiClientService;
import com.englishweb.english_web_be.service.ScoreWritingService;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class ScoreWritingServiceImpl implements ScoreWritingService {
    GeminiClientService geminiClientService;
    TextRazorServiceImpl textRazorServiceImpl;

    public ScoreWritingServiceImpl(TextRazorServiceImpl textRazorServiceImpl, GeminiClientService geminiClientService) {
        this.textRazorServiceImpl = textRazorServiceImpl;
        this.geminiClientService = geminiClientService;
    }

    public ScoreResponse scoreWriting(String text, String topic) {
        try {
            AnalyzedText analyzedText = textRazorServiceImpl.analyzeText(text);
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append("Please evaluate the following text based on its relevance to the topic '")
                    .append(topic)
                    .append("'. The text should be graded on a scale from 0 to 100, where 100 means highly relevant.\n\n");
            promptBuilder.append("Text: ").append(text).append("\n\n");
            promptBuilder.append("Consider the following topics for scoring:\n");
            for (Topic identifiedTopic : analyzedText.getResponse().getTopics()) {
                promptBuilder.append("- Topic: ")
                        .append(identifiedTopic.getLabel())
                        .append(" (Score: ")
                        .append(identifiedTopic.getScore())
                        .append(")\n");
            }

            promptBuilder.append("Provide a brief evaluation (max 30 words) for the following text. The evaluation should include: ");
            promptBuilder.append("1. Grammar (whether it's correct or any suggested improvements). ");
            promptBuilder.append("2. Vocabulary accuracy (whether the words used are appropriate). ");
            promptBuilder.append("3. Relevance to the topic (how well the text stays on topic). ");
            promptBuilder.append("Provide the evaluation in the following format:\n");
            promptBuilder.append("Score: [score/100] (on the first line) and the comments (on the second line, max 30 words).");
            String prompt = promptBuilder.toString();
            log.info("Send prompt: {}", prompt);
            String response = geminiClientService.generateText(prompt);
            log.info("Response from gemini: {}", response);
            String[] parts = response.split("\n");
            String scoreText = parts[0];
            String comment = String.join("\n", Arrays.copyOfRange(parts, 1, parts.length));
            log.info("Score Text: {}", scoreText);
            log.info("Comment: {}", comment);
            return ScoreResponse.builder()
                    .score(scoreText)
                    .comment(comment)
                    .build();
        } catch (AnalysisException | NetworkException e) {
            throw new RuntimeException(e);
        }
    }
}
