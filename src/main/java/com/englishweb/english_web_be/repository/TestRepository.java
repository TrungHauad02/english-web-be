package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, String>  , JpaSpecificationExecutor<Test> {

    Page<Test> findAllByType(Pageable pageable, TestTypeEnum type);

    Test findByIdAndStatus(String id, StatusEnum status);


}