package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;

import java.util.List;

public interface ReadingAnswerService extends BaseService<ReadingAnswerDTO> {

    List<ReadingAnswerDTO> findAllByQuestionId(String questionId);
}
