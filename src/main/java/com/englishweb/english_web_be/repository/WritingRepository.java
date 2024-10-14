package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Writing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WritingRepository extends JpaRepository<Writing, String> {
    @Query("select w from Writing w")
    Page<Writing> findAllWritings(Pageable pageable);
}
