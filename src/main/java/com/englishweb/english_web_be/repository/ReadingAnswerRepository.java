package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.ReadingAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingAnswerRepository extends JpaRepository<ReadingAnswer, String> {
    List<ReadingAnswer> findAllByQuestion_Id(String questionId);
}
