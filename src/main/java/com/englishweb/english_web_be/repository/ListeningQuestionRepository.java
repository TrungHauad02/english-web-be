package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.ListeningQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListeningQuestionRepository extends JpaRepository<ListeningQuestion, String> {
    List<ListeningQuestion> findAllByListening_Id(String listeningId);
}
