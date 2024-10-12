package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Vocabulary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VocabularyRepository extends JpaRepository<Vocabulary, String> {
    @Query("SELECT v FROM Vocabulary v WHERE v.topic.id = :topicId")
    Page<Vocabulary> retrieveVocabsInTopicByPage(Pageable pageable,  @Param("topicId") String topicId);
}
