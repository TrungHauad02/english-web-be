package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SubmitTestListeningAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitTestListeningAnswerRepository extends JpaRepository<SubmitTestListeningAnswer, String> {
    List<SubmitTestListeningAnswer> findAllBySubmitTest_Id(String submitTestId);
}
