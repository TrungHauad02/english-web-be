package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.dto.request.ListeningQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningQuestionResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface ListeningQuestionService extends BaseService<ListeningQuestionRequestDTO, ListeningQuestionResponseDTO> {

    List<ListeningQuestionResponseDTO> findByListeningId(String listeningId);

    List<ListeningQuestionResponseDTO> findByListeningIdAndStatus(String listeningId, StatusEnum status);

    List<ListeningQuestionDTO> findDTOByListeningId(String listeningId);
}
