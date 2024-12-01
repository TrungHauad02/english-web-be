package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestListening;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestListeningRepository extends JpaRepository<TestListening,String> {
    List<TestListening> findAllByTest_Id(String test_id);
    List<TestListening> findAllByTest_IdAndStatus(String test_id, StatusEnum status);
}
