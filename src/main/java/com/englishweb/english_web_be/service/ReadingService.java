package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.request.ReadingRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface ReadingService extends BaseService<ReadingRequestDTO, ReadingResponseDTO> {

    Page<ReadingResponseDTO> findReadingWithStatusAndPagingAndSorting(StatusEnum status, int page, int size, String sortBy, String sortDir, Class<ReadingResponseDTO> dtoClass);
}
