package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface ReadingQuestionService extends BaseService<ReadingQuestionDTO> {

    List<ReadingQuestionDTO> findAllByReadingId(String readingId);

    List<ReadingQuestionDTO> findAllByReadingIdAndStatus(String readingId, StatusEnum status);
}
