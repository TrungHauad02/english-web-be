package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.*;

import com.englishweb.english_web_be.service.TestService;

import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {
    TestService testService;


    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/tests")
    public Page<TestDTO> retrieveTopicsByPage(@RequestParam int page, @RequestParam String type) {
        return testService.retrieveTestsByPage(page, type);
    }
}
