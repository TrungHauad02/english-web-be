package com.englishweb.english_web_be.controller;


import com.englishweb.english_web_be.dto.TestVocabularyAnswerDTO;
import com.englishweb.english_web_be.dto.TestVocabularyQuestionDTO;
import com.englishweb.english_web_be.service.TestVocabularyAnswerService;
import com.englishweb.english_web_be.service.TestVocabularyQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestVocabularyAnswerController {

    TestVocabularyAnswerService   testVocabularyAnswerService;

    public TestVocabularyAnswerController(TestVocabularyAnswerService testVocabularyAnswerService) {
        this.testVocabularyAnswerService = testVocabularyAnswerService;
    }

    @GetMapping("/api/testvocabularyanswer/{id}")
    public ResponseEntity<TestVocabularyAnswerDTO> retrieveTopicById(@PathVariable String id){

        return new ResponseEntity<>(testVocabularyAnswerService.findById(id), HttpStatus.OK);
    }


    @PostMapping("/api/testvocabularyanswer")
    public ResponseEntity<TestVocabularyAnswerDTO> createTest(@Valid @RequestBody TestVocabularyAnswerDTO dto){
        TestVocabularyAnswerDTO createdTest = testVocabularyAnswerService.create(dto);

        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

    @PutMapping("/api/testvocabularyanswer")
    public ResponseEntity<TestVocabularyAnswerDTO> updateTest(@Valid @RequestBody TestVocabularyAnswerDTO dto){
        TestVocabularyAnswerDTO updatedTest = testVocabularyAnswerService.update(dto);

        return ResponseEntity.ok(updatedTest);
    }

    @DeleteMapping("/api/testvocabularyanswer/{id}")
    public ResponseEntity<Void> deleteTestById(@PathVariable String id){
        testVocabularyAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
