package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Listening;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ListeningRepository extends JpaRepository<Listening, String>, JpaSpecificationExecutor<Listening> {
    @Query("select l from Listening l")
    Page<Listening> findAllListening(Pageable pageable);

    @Query("select l from Listening l where l.status = :status")
    Page<Listening> findAllListeningByStatus(StatusEnum status, Pageable pageable);
}
