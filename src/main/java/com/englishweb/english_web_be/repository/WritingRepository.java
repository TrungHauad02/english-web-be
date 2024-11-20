package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Writing;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface WritingRepository extends JpaRepository<Writing, String>, JpaSpecificationExecutor<Writing> {
    @Query("select w from Writing w")
    Page<Writing> findAllWritings(Pageable pageable);

    @Query("select w from Writing w where w.status = :status")
    Page<Writing> findAllWritingByStatus(StatusEnum status, Pageable pageable);
}
