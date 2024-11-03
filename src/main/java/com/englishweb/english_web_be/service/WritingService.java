package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.request.WritingRequestDTO;
import com.englishweb.english_web_be.dto.response.WritingResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface WritingService extends BaseService<WritingRequestDTO, WritingResponseDTO> {

    Page<WritingResponseDTO> findWritingWithStatusAndPagingAndSorting(StatusEnum status, int page, int size, String sortBy, String sortDir, Class<WritingResponseDTO> dtoClass);
}
