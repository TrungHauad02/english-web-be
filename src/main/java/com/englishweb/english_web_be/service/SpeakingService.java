package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.request.SpeakingRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface SpeakingService extends BaseService<SpeakingRequestDTO, SpeakingResponseDTO> {

    Page<SpeakingResponseDTO> findSpeakingWithStatusAndPagingAndSorting(StatusEnum status, int page, int size, String sortBy, String sortDir, Class<SpeakingResponseDTO> dtoClass);
}
