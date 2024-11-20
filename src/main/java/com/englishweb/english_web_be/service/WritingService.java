package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface WritingService extends BaseService<WritingDTO> {

    Page<WritingDTO> findWithPagingSortingSearching(
            String title, StatusEnum status, int page, int size, String sortBy, String sortDir,
            Class<WritingDTO> dtoClass);
}
