package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, String> {

    Page<Test> findAllByType(Pageable pageable, TestTypeEnum type);

    List<Test> findAllByType(TestTypeEnum type);


}