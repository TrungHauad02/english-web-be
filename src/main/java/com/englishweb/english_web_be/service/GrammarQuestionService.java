package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface GrammarQuestionService extends BaseService<GrammarQuestionDTO> {

    List<GrammarQuestionDTO> findAllByGrammarId(String grammarId);

    List<GrammarQuestionDTO> findAllByGrammarIdAndStatus(String grammarId, StatusEnum status);
}
