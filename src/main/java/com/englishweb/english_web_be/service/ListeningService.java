package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.request.ListeningRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface ListeningService extends BaseService<ListeningRequestDTO, ListeningResponseDTO> {

    Page<ListeningResponseDTO> findListeningWithStatusAndPagingAndSorting(
            StatusEnum status, int page, int size, String sortBy, String sortDir,
            Class<ListeningResponseDTO> dtoClass);
}
