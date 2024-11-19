package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.OpenAIResponseDTO;
import com.englishweb.english_web_be.service.CommentListeningQuestionService;
import com.englishweb.english_web_be.util.OpenAIModel;
import org.springframework.stereotype.Service;

@Service
public class CommentListeningQuestionServiceImpl implements CommentListeningQuestionService {
    private final OpenAIServiceImpl openAIServiceImpl;

    public CommentListeningQuestionServiceImpl(OpenAIServiceImpl openAIServiceImpl) {
        this.openAIServiceImpl = openAIServiceImpl;
    }

    public String commentListeningQuestion(String questionContent, String listeningTranscript, String[] answers, String userAnswer) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Given the following listening transcript, question content, and related answers, generate a detailed comment on the user's selected answer:\n");
        promptBuilder.append("Listening Transcript: ").append(listeningTranscript).append("\n");
        promptBuilder.append("Question Content: ").append(questionContent).append("\n");
        promptBuilder.append("Answers:\n");
        for (int i = 0; i < answers.length; i++) {
            promptBuilder.append(i + 1).append(". ").append(answers[i]).append("\n");
        }
        promptBuilder.append("User's answer: ").append(userAnswer).append("\n");
        promptBuilder.append("Provide a comprehensive comment on the user's answer, analyzing its correctness and offering suggestions for improvement.");

        String prompt = promptBuilder.toString();

        OpenAIResponseDTO responseDTO = openAIServiceImpl.generateResponse(OpenAIModel.GPT_4, prompt, 100, 0.1);
        String comment = responseDTO.getChoices().get(0).getText().trim();

        return comment;
    }
}
