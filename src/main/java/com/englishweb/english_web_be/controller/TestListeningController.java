package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.model.TestListening;

import com.englishweb.english_web_be.repository.TestListeningRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestListeningController {

    private final TestListeningRepository testListeningRepository;

    @Autowired
    public TestListeningController(TestListeningRepository testListeningRepository) {
        this.testListeningRepository = testListeningRepository;
    }
    @GetMapping("/listening")
    public ResponseEntity<List<TestListeningDTO>> retrieveTestListeningByTestId(
            @RequestParam String testId) {


        List<TestListening> testListeningList = testListeningRepository.findAllByTest_Id(testId);
        if (testListeningList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<TestListeningDTO> testListeningDTOList = testListeningList.stream().map(testListening -> {

            List<TestListeningQuestionDTO> questionDTOs = testListening.getQuestions().stream().map(question -> {

                List<TestListeningAnswerDTO> answerDTOs = question.getAnswersList().stream()
                        .map(answer -> new TestListeningAnswerDTO(
                                answer.getId(),
                                answer.getContent(),
                                answer.getIsCorrect()))
                        .collect(Collectors.toList());

   
                return new TestListeningQuestionDTO(
                        question.getId(),
                        question.getContent(),
                        question.getSerial(),
                        answerDTOs
                );
            }).collect(Collectors.toList());


            return new TestListeningDTO(
                    testListening.getId(),
                    testListening.getSerial(),
                    testListening.getContent(),
                    testListening.getTranscript(),
                    questionDTOs
            );
        }).collect(Collectors.toList());


        return ResponseEntity.ok(testListeningDTOList);
    }

}


