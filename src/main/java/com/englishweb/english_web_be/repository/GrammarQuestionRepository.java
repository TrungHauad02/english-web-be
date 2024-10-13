package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.GrammarQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrammarQuestionRepository extends JpaRepository<GrammarQuestion, String> {
    List<GrammarQuestion> findAllByGrammar_Id(String grammarId);
}
