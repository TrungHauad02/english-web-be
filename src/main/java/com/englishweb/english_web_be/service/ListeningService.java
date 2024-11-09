package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface ListeningService extends BaseService<ListeningDTO> {

    Page<ListeningDTO> findListeningWithStatusAndPagingAndSorting(
            StatusEnum status, int page, int size, String sortBy, String sortDir,
            Class<ListeningDTO> dtoClass);
}
