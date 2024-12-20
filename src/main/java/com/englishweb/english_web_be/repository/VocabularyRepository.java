package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Vocabulary;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VocabularyRepository extends JpaRepository<Vocabulary, String> , JpaSpecificationExecutor<Vocabulary> {
    List<Vocabulary> findAllByTopic_Id(String topicId);
}
