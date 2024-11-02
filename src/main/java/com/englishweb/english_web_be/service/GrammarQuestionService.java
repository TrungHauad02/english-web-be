package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.dto.request.GrammarQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarQuestionResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface GrammarQuestionService extends BaseService<GrammarQuestionRequestDTO, GrammarQuestionResponseDTO> {

    List<GrammarQuestionResponseDTO> findAllByGrammarId(String grammarId);

    List<GrammarQuestionResponseDTO> findAllByGrammarIdAndStatus(String grammarId, StatusEnum status);

    List<GrammarQuestionDTO> findAllDTOByGrammarId(String grammarId);
}
