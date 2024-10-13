package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.TestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    TestRepository repository;
    public TestController(TestRepository repository){this.repository = repository;}
    @GetMapping("/tests")
    public Page<Test> retrieveTestsByPage(@RequestParam int page, @RequestParam String type) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("serial"));

        TestTypeEnum testType = TestTypeEnum.valueOf(type);
        return repository.findAllByType(pageable, testType);
    }
    @PostMapping("/tests")
    public Test creattest(@RequestBody Test test){
        return repository.save(test);
    }

}
