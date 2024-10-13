package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.GrammarAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrammarAnswerRepository extends JpaRepository<GrammarAnswer, String> {
    List<GrammarAnswer> findAllByQuestion_Id(String questionId);
}
