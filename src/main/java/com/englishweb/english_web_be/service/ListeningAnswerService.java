package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;

import java.util.List;

public interface ListeningAnswerService extends BaseService<ListeningAnswerDTO> {

    List<ListeningAnswerDTO> findByQuestionId(String questionId);
}
