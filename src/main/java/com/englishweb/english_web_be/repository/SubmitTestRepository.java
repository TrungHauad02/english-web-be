package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SubmitTest;
import com.englishweb.english_web_be.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SubmitTestRepository extends JpaRepository<SubmitTest, String> , JpaSpecificationExecutor<SubmitTest> {

     List<SubmitTest> findAllByTest_Id(String testId);
     List<SubmitTest> findAllByTest_IdAndUserId(String testId,String userId);
     boolean existsByTest_Id(String testId);
}
