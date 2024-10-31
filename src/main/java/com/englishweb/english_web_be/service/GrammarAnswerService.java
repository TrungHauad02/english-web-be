package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;

import java.util.List;

public interface GrammarAnswerService extends BaseService<GrammarAnswerDTO> {

    List<GrammarAnswerDTO> findAllByQuestionId(String questionId);
}
