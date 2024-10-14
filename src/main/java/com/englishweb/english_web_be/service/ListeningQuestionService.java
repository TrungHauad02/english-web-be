package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.model.ListeningQuestion;
import com.englishweb.english_web_be.repository.ListeningQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningQuestionService {
    ListeningQuestionRepository repository;
    ListeningAnswerService answerService;

    public ListeningQuestionService(ListeningQuestionRepository repository, ListeningAnswerService answerService) {
        this.repository = repository;
        this.answerService = answerService;
    }

    public List<ListeningQuestionDTO> retrieveListeningQuestionsByListenPracticeId(String listenPracticeId) {
        List<ListeningQuestion> entityList = repository.findAllByListenPractice_Id(listenPracticeId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private ListeningQuestionDTO convertToDTO(ListeningQuestion entity) {
        ListeningQuestionDTO dto = new ListeningQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        dto.setAnswers(answerService.retrieveListeningAnswersByQuestionId(entity.getId()));
        return dto;
    }
}
