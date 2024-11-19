package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.CommentResponse;
import com.englishweb.english_web_be.dto.response.OpenAIResponseDTO;
import com.englishweb.english_web_be.service.CommentMixingQuestionService;
import com.englishweb.english_web_be.util.OpenAIModel;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import org.springframework.stereotype.Service;

@Service
public class CommentMixingQuestionServiceImpl implements CommentMixingQuestionService {
    private final OpenAIServiceImpl openAIServiceImpl;

    public CommentMixingQuestionServiceImpl(OpenAIServiceImpl openAIServiceImpl) {
        this.openAIServiceImpl = openAIServiceImpl;
    }

    public CommentResponse commentMixingQuestion(String question, String[] answers, String userAnswer) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Given the following question and answers, generate a detailed comment on the user's answer:\n");
        promptBuilder.append("Question: ").append(question).append("\n");
        promptBuilder.append("Answers:\n");
        for (int i = 0; i < answers.length; i++) {
            promptBuilder.append(i + 1).append(". ").append(answers[i]).append("\n");
        }
        promptBuilder.append("User's answer: ").append(userAnswer).append("\n");
        promptBuilder.append("Provide a comprehensive comment on the user's answer, including its correctness and suggestions for improvement if needed.");

        String prompt = promptBuilder.toString();

        OpenAIResponseDTO responseDTO = openAIServiceImpl.generateResponse(OpenAIModel.GPT_4, prompt, 100, 0.1);
        String comment = responseDTO.getChoices().get(0).getText().trim();
        return CommentResponse.builder()
                .comment(comment)
                .build();

    }
}
