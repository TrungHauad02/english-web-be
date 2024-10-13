package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.ListenAndWriteAWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListenAndWriteAWordRepository extends JpaRepository<ListenAndWriteAWord, String> {
    List<ListenAndWriteAWord> findAllByListening_Id(String ListeningId);
}
