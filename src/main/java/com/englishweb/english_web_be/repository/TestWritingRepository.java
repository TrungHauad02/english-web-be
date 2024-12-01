package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Test;

import com.englishweb.english_web_be.model.TestWriting;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestWritingRepository extends JpaRepository<TestWriting, String> {
    List<TestWriting> findAllByTest_Id(String test_id);
    List<TestWriting> findAllByTest_IdAndStatus(String test_id, StatusEnum status);
}
