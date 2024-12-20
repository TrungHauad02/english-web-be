package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface GrammarService extends BaseService<GrammarDTO> {

    Page<GrammarDTO> findWithPagingSortingSearching(String title, StatusEnum status, int page, int size, String sortBy, String sortDir, Class<GrammarDTO> grammarResponseDTOClass);
}
