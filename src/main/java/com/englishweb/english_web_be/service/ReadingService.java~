package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface ReadingService extends BaseService<ReadingDTO> {

    Page<ReadingDTO> findReadingWithStatusAndPagingAndSorting(StatusEnum status, int page, int size, String sortBy, String sortDir, Class<ReadingDTO> dtoClass);
}
