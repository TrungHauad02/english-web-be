package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Test;

import com.englishweb.english_web_be.model.TestWriting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestWritingRepository extends JpaRepository<TestWriting, String> {
    List<TestWriting> findAllByTest_Id(String test_id);
}
