package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SubmitTestReadingAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitTestReadingAnswerRepository extends JpaRepository<SubmitTestReadingAnswer, String> {
    List<SubmitTestReadingAnswer> findAllBySubmitTest_Id(String submitTestId);
}
