package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestListeningQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestListeningQuestionRepository extends JpaRepository<TestListeningQuestion,String> {
    List<TestListeningQuestion> findAllByTestListening_Id(String testListeningId);
}
