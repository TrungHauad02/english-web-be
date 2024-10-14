package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.ListeningAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListeningAnswerRepository extends JpaRepository<ListeningAnswer, String> {
    List<ListeningAnswer> findAllByQuestion_Id(String questionId);
}
