package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.dto.request.ListeningQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningQuestionResponseDTO;
import com.englishweb.english_web_be.service.ListeningAnswerService;
import org.springframework.stereotype.Component;

@Component
public class ListeningQuestionMapper implements BaseMapper<ListeningQuestionDTO, ListeningQuestionRequestDTO, ListeningQuestionResponseDTO> {
    private final ListeningAnswerService listeningAnswerService;

    public ListeningQuestionMapper(ListeningAnswerService listeningAnswerService) {
        this.listeningAnswerService = listeningAnswerService;
    }

    @Override
    public ListeningQuestionDTO mapToDTO(ListeningQuestionRequestDTO requestDTO) {
        return ListeningQuestionDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .serial(requestDTO.getSerial())
                .explanation(requestDTO.getExplanation())
                .status(requestDTO.getStatus())
                .answers(listeningAnswerService.findDTOByQuestionId(requestDTO.getId()))
                .listeningId(requestDTO.getListeningId())
                .build();
    }

    @Override
    public ListeningQuestionResponseDTO mapToResponseDTO(ListeningQuestionDTO dto) {
        return ListeningQuestionResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .serial(dto.getSerial())
                .explanation(dto.getExplanation())
                .status(dto.getStatus())
                .answers(dto.getAnswers())
                .listeningId(dto.getListeningId())
                .build();
    }
}
