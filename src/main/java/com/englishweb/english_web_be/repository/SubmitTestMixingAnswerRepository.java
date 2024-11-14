package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SubmitTestMixingAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitTestMixingAnswerRepository extends JpaRepository<SubmitTestMixingAnswer, String> {
    List<SubmitTestMixingAnswer> findAllBySubmitTest_Id(String submitTestId);
}
