package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestDTO;
import com.englishweb.english_web_be.dto.TestVocabularyQuestionDTO;
import com.englishweb.english_web_be.model.TestVocabularyQuestion;
import com.englishweb.english_web_be.service.TestVocabularyQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestVocabularyQuestionController {
    TestVocabularyQuestionService testVocabularyQuestionService;

    public TestVocabularyQuestionController(TestVocabularyQuestionService testVocabularyQuestionService) {
        this.testVocabularyQuestionService = testVocabularyQuestionService;
    }

    @GetMapping("/api/testvocabularyquestion/{id}")
    public ResponseEntity<TestVocabularyQuestionDTO> retrieveTopicById(@PathVariable String id){

        return new ResponseEntity<>(testVocabularyQuestionService.findById(id), HttpStatus.OK);
    }


    @PostMapping("/api/testvocabularyquestion")
    public ResponseEntity<TestVocabularyQuestionDTO> createTest(@Valid @RequestBody TestVocabularyQuestionDTO dto){
        return new ResponseEntity<>(testVocabularyQuestionService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testvocabularyquestion/{id}")
    public ResponseEntity<TestVocabularyQuestionDTO> updateTest(@Valid @RequestBody TestVocabularyQuestionDTO dto, @PathVariable String id){
        return ResponseEntity.ok(testVocabularyQuestionService.update(dto, id));
    }

    @DeleteMapping("/api/testvocabularyquestion/{id}")
    public ResponseEntity<Void> deleteTestById(@PathVariable String id){
        testVocabularyQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
