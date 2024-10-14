package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Speaking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpeakingRepository extends JpaRepository<Speaking, String>{
    @Query("select s from Speaking s")
    Page<Speaking> findAllSpeakings(Pageable pageable);
}
