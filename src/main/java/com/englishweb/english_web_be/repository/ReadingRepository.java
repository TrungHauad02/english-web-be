package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Reading;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ReadingRepository extends JpaRepository<Reading, String>, JpaSpecificationExecutor<Reading> {
    @Query("select r from Reading r")
    Page<Reading> findAllReadings(Pageable pageable);

    @Query("select r from Reading r where r.status = :status")
    Page<Reading> findAllReadingsByStatus(StatusEnum status, Pageable pageable);
}
