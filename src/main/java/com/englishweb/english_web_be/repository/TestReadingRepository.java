package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestReading;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestReadingRepository extends JpaRepository<TestReading, String> {
    List<TestReading> findAllByTest_Id(String testId);
    List<TestReading> findAllByTest_IdAndStatus(String testId, StatusEnum status);
}
