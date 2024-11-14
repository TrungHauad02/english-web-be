package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SubmitTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitTestRepository extends JpaRepository<SubmitTest, String> {
}
