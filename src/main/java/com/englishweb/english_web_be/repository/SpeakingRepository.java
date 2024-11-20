package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Speaking;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SpeakingRepository extends JpaRepository<Speaking, String>, JpaSpecificationExecutor<Speaking> {
    @Query("select s from Speaking s")
    Page<Speaking> findAllSpeakings(Pageable pageable);

    @Query("select s from Speaking s where s.status = :status")
    Page<Speaking> findAllSpeakingByStatus(StatusEnum status, Pageable pageable);
}
