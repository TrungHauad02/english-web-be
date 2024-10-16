package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.ListenPractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListenPracticeRepository extends JpaRepository<ListenPractice, String> {
    @Query("Select l from ListenPractice l")
    List<ListenPractice> findAllByListening_Id(String listeningId);

    ListenPractice findByListening_Id(String listeningId);
}
