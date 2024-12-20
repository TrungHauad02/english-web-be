package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.ScoreCommentResponse;
import com.englishweb.english_web_be.service.GeminiClientService;
import com.englishweb.english_web_be.service.ScoreWritingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScoreWritingServiceImpl implements ScoreWritingService {
    GeminiClientService geminiClientService;
    TextRazorServiceImpl textRazorServiceImpl;

    public ScoreWritingServiceImpl(TextRazorServiceImpl textRazorServiceImpl, GeminiClientService geminiClientService) {
        this.textRazorServiceImpl = textRazorServiceImpl;
        this.geminiClientService = geminiClientService;
    }

    public ScoreCommentResponse scoreWriting(String text, String topic) {
        try {
            AnalyzedText analyzedText = textRazorServiceImpl.analyzeText(text);
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append("Act as a professional English teacher. You will be provided with a text and a topic to evaluate. ")
                    .append("Your task is to analyze the text and provide a score and a comment on its relevance to the topic. ");
            if(analyzedText.getResponse().getTopics() != null && !analyzedText.getResponse().getTopics().isEmpty()) {
                promptBuilder.append("Evaluate the following text based on its relevance to the topic '''")
                        .append(topic)
                        .append("'''. The text should be graded on a scale from 0 to 100, where 100 means highly relevant.\n\n");
                promptBuilder.append("Text: ").append(text).append("\n\n");
                promptBuilder.append("Consider the following topics for scoring:\n");
                for (Topic identifiedTopic : analyzedText.getResponse().getTopics()) {
                    promptBuilder.append("- Topic: ")
                            .append(identifiedTopic.getLabel())
                            .append(" (Score: ")
                            .append(identifiedTopic.getScore())
                            .append(")\n");
                }
            } else {
                promptBuilder.append("Evaluate the following text based on its relevance to the topic '''")
                        .append(topic)
                        .append("'''");
                promptBuilder.append("Text: ").append(text).append("\n\n");
            }

            promptBuilder.append("Provide a brief evaluation (max 30 words) for the following text. The evaluation should include: ");
            promptBuilder.append("1. Grammar (whether it's correct or any suggested improvements). ");
            promptBuilder.append("2. Vocabulary accuracy (whether the words used are appropriate). ");
            promptBuilder.append("3. Relevance to the topic (how well the text stays on topic). ");
            promptBuilder.append("Provide the evaluation in the following format in json:\n");
            promptBuilder.append("{\n");
            promptBuilder.append("  \"score\": \"<score>/100\",\n");
            promptBuilder.append("  \"comment\": <comment>\n");
            promptBuilder.append("}\n");
            promptBuilder.append("Don't be delusional and give exactly format as above. If there is no relevance to the topic, score it as 0.\n\n");
            String prompt = promptBuilder.toString();
            log.info("Send prompt: {}", prompt);
            String response = geminiClientService.generateText(prompt);
            log.info("Response from gemini: {}", response);
            int firstNewLine = response.indexOf('\n');
            int lastNewLine = response.lastIndexOf('\n');
            if (firstNewLine != -1 && lastNewLine != -1 && firstNewLine < lastNewLine) {
                response = response.substring(firstNewLine + 1, lastNewLine);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            String scoreText = jsonNode.get("score").asText();
            String comment = jsonNode.get("comment").asText();
            log.info("Score Text: {}", scoreText);
            log.info("Comment: {}", comment);
            return ScoreCommentResponse.builder()
                    .score(scoreText)
                    .comment(comment)
                    .build();
        } catch (AnalysisException | NetworkException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
