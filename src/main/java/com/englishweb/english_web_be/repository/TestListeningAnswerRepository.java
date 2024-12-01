package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestListeningAnswer;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestListeningAnswerRepository extends JpaRepository<TestListeningAnswer,String> {
    List<TestListeningAnswer> findAllByTestListeningQuestion_Id(String testListeningQuestionId);
    List<TestListeningAnswer> findAllByTestListeningQuestion_IdAndStatus(String testListeningQuestionId, StatusEnum status);
}
