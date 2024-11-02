package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.request.GrammarRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface GrammarService extends BaseService<GrammarRequestDTO, GrammarResponseDTO> {

    Page<GrammarResponseDTO> findGrammarWithStatusAndPagingAndSorting(StatusEnum status, int page, int size, String sortBy, String sortDir, Class<GrammarResponseDTO> grammarResponseDTOClass);
}
