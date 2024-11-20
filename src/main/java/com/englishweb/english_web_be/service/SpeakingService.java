package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface SpeakingService extends BaseService<SpeakingDTO> {

    Page<SpeakingDTO> findWithPagingSortingSearching(String title, StatusEnum status, int page, int size,
                                                     String sortBy, String sortDir,
                                                     Class<SpeakingDTO> dtoClass);
}
