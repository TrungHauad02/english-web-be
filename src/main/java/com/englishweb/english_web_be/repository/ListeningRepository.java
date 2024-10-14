package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Listening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListeningRepository extends JpaRepository<Listening, String> {
    @Query("select l from Listening l")
    Page<Listening> findAllListening(Pageable pageable);
}