package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestReadingAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestReadingAnswerRepository extends JpaRepository<TestReadingAnswer,String> {
    List<TestReadingAnswer> findAllByTestReadingQuestion_Id(String testQuestion_id);
}
