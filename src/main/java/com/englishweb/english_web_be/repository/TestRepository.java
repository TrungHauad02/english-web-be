package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.model.TestTypeEnum;
import com.englishweb.english_web_be.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestRepository extends JpaRepository<Test, String> {
    @Query("SELECT t FROM Test t")
    Page<Test> findAllTests(Pageable pageable);
    Page<Test> findAllByType(Pageable pageable, TestTypeEnum type);
}
