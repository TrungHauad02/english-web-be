package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningQuestionDTO;

import java.util.List;

public interface ListeningQuestionService extends BaseService<ListeningQuestionDTO> {

    List<ListeningQuestionDTO> findByListeningId(String listeningId);
}
