package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestSpeaking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestSpeakingRepository extends JpaRepository<TestSpeaking,String> {
    TestSpeaking findByTest_Id(String test_id);
}
