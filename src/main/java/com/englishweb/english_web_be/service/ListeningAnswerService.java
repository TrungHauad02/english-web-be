package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.model.ListeningAnswer;
import com.englishweb.english_web_be.repository.ListeningAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningAnswerService {
    ListeningAnswerRepository repository;

    public ListeningAnswerService(ListeningAnswerRepository repository) {
        this.repository = repository;
    }

    public List<ListeningAnswerDTO> retrieveListeningAnswersByQuestionId(String questionId) {
        List<ListeningAnswer> enityList = repository.findAllByQuestion_Id(questionId);
        return enityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private ListeningAnswerDTO convertToDTO(ListeningAnswer entity) {
        ListeningAnswerDTO dto = new ListeningAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCorrect(entity.isCorrect());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
