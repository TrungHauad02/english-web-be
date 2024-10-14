package com.englishweb.english_web_be.repository;



import com.englishweb.english_web_be.model.TestListening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestListeningRepository extends JpaRepository<TestListening, String> {
    @Query("SELECT t FROM TestListening t WHERE t.test.id = :testId")
    List<TestListening> findAllByTest_Id( String testId);
}
