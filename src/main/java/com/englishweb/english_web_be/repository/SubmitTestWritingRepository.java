package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SubmitTestWriting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitTestWritingRepository extends JpaRepository<SubmitTestWriting, String> {
    List<SubmitTestWriting> findAllBySubmitTest_Id(String submitTestId);
}
