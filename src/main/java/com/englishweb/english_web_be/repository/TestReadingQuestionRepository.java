package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestReadingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestReadingQuestionRepository extends JpaRepository<TestReadingQuestion,String> {
    List<TestReadingQuestion> findAllByTestReading_Id(String testReadingId);
}
