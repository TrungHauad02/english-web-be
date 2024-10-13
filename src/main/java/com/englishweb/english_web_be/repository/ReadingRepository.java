package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Reading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReadingRepository extends JpaRepository<Reading, String> {
    @Query("select r from Reading r")
    Page<Reading> findAllReadings(Pageable pageable);
}
