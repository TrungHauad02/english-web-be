package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.*;

import com.englishweb.english_web_be.service.TestService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TestController {
    TestService testService;


    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/api/tests")
    public Page<TestDTO> retrieveTestsByPage(@RequestParam int page, @RequestParam String type) {
        return testService.retrieveTestsByPage(page, type);
    }

    @GetMapping("/api/testsall")
    public List<TestDTO> retrieveTestsBytype(@RequestParam String type) {
        return (List<TestDTO>) testService.retrieveTestsallBytype(type);
    }



    @GetMapping("/api/tests/{id}")
    public ResponseEntity<TestDTO> retrieveTopicById(@PathVariable String id){

        return new ResponseEntity<>(testService.findById(id), HttpStatus.OK);
    }


    @PostMapping("/api/tests")
    public ResponseEntity<TestDTO> createTest(@Valid @RequestBody TestDTO dto){
        TestDTO createdTest = testService.create(dto);

        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

    @PutMapping("/api/tests")
    public ResponseEntity<TestDTO> updateTest(@Valid @RequestBody TestDTO dto){
        TestDTO updatedTest = testService.update(dto);

        return ResponseEntity.ok(updatedTest);
    }

    @DeleteMapping("/api/tests/{id}")
    public ResponseEntity<Void> deleteTestById(@PathVariable String id){
        testService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
