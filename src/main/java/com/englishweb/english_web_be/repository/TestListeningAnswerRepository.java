package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestListeningAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestListeningAnswerRepository extends JpaRepository<TestListeningAnswer,String> {
    List<TestListeningAnswer> findAllByTestListeningQuestion_Id(String testListeningQuestionId);
}
