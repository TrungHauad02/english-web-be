package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Vocabulary;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VocabularyRepository extends JpaRepository<Vocabulary, String> {
    Page<Vocabulary> findAllByTopic_Id(Pageable pageable, String topicId);
    List<Vocabulary> findAllByTopic_Id(String topicId);

    @Query("select v from Vocabulary v where v.status = :status and v.topic.id = :topicId")
    Page<Vocabulary> findAllByStatusAndTopic_Id(@Param("status") StatusEnum status, @Param("topicId") String topicId, Pageable pageable);
}
