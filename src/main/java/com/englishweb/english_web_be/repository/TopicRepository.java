package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Topic;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, String>, JpaSpecificationExecutor<Topic> {
    @Query("SELECT t FROM Topic t")
    Page<Topic> findAllTopics(Pageable pageable);

    @Query("SELECT t FROM Topic t WHERE t.status = :status")
    Page<Topic> findTopicWithStatus(StatusEnum status, Pageable pageable);
}
