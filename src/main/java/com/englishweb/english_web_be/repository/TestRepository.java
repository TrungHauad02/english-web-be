package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, String>  , JpaSpecificationExecutor<Test> {


    Test findByIdAndStatus(String id, StatusEnum status);
    @NotNull List<Test> findAll();
    @Query("SELECT MAX(t.serial) FROM Test t")
    Integer findMaxSerial();


}