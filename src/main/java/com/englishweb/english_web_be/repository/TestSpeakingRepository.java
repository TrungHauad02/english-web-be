package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestSpeaking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestSpeakingRepository extends JpaRepository<TestSpeaking,String> {
    List<TestSpeaking> findAllByTest_Id(String test_id);
}
