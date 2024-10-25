package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.ReadingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingQuestionRepository extends JpaRepository<ReadingQuestion, String> {
    List<ReadingQuestion> findAllByReading_Id(String readingId);
}
