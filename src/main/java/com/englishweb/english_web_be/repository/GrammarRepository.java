package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Grammar;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface GrammarRepository extends JpaRepository<Grammar, String>, JpaSpecificationExecutor<Grammar> {
    @Query("SELECT g from Grammar g")
    Page<Grammar> findAllGrammars(Pageable pageable);

    @Query("SELECT g from Grammar g where g.status = :status")
    Page<Grammar> findGrammarWithStatus(StatusEnum status, Pageable pageable);

}
