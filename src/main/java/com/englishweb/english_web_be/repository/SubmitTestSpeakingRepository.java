package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SubmitTestSpeaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitTestSpeakingRepository extends JpaRepository<SubmitTestSpeaking, String> {
    List<SubmitTestSpeaking> findAllBySubmitTest_Id(String submitTestId);
}
