package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.OpenAIResponseDTO;
import com.englishweb.english_web_be.dto.response.ScoreResponse;
import com.englishweb.english_web_be.service.ScoreWritingService;
import com.englishweb.english_web_be.util.OpenAIModel;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Topic;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class ScoreWritingServiceImpl implements ScoreWritingService {
    private final OpenAIServiceImpl openAIServiceImpl;
    TextRazorServiceImpl textRazorServiceImpl;

    public ScoreWritingServiceImpl(TextRazorServiceImpl textRazorServiceImpl, OpenAIServiceImpl openAIServiceImpl) {
        this.textRazorServiceImpl = textRazorServiceImpl;
        this.openAIServiceImpl = openAIServiceImpl;
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
            promptBuilder.append("\nPlease also evaluate the following aspects of the text:\n");
            promptBuilder.append("- Grammar: Assess the grammatical correctness of the text.\n");
            promptBuilder.append("- Vocabulary: Evaluate the appropriateness and accuracy of the vocabulary used in the text.\n");
            promptBuilder.append("\nProvide a score and feedback on the relevance of the text to the topic, grammar, and vocabulary, and check for any errors.\n");

            promptBuilder.append("\nProvide a score (from 0 to 100) and a detailed comment for each aspect: relevance to the topic, grammar, and vocabulary.");
            String prompt = promptBuilder.toString();

            OpenAIResponseDTO responseDTO = openAIServiceImpl.generateResponse(OpenAIModel.GPT_4, prompt, 100, 0.1);
            String resultText = responseDTO.getChoices().get(0).getText().trim();
            String[] parts = resultText.split("\n");
            String scoreText = parts[0];
            String comment = String.join("\n", Arrays.copyOfRange(parts, 1, parts.length));

            return ScoreResponse.builder()
                    .score(Integer.parseInt(scoreText))
                    .comment(comment)
                    .build();
        } catch (AnalysisException | NetworkException e) {
            throw new RuntimeException(e);
        }
    }
}
