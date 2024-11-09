package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.*;
import com.englishweb.english_web_be.dto.request.TestRequestDTO;
import com.englishweb.english_web_be.dto.response.TestResponseDTO;
import com.englishweb.english_web_be.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class TestController {
    TestService testService;


    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/api/tests")
    public Page<TestResponseDTO> retrieveTestsByPage(@RequestParam int page, @RequestParam String type) {
        return testService.retrieveTestsByPage(page, type);
    }

    @GetMapping("/api/testsall")
    public List<TestResponseDTO> retrieveTestsBytype(@RequestParam String type) {
        return (List<TestResponseDTO>) testService.retrieveTestsAllByType(type);
    }



    @GetMapping("/api/tests/{id}")
    public ResponseEntity<TestResponseDTO> retrieveTopicById(@PathVariable String id){

        return new ResponseEntity<>(testService.findById(id), HttpStatus.OK);
    }


    @PostMapping("/api/test")
    public ResponseEntity<TestResponseDTO> createTest(@Valid @RequestBody TestRequestDTO dto){

        TestResponseDTO createdTest = testService.create(dto);

        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }
    @PostMapping("/api/test/question/delete")
    public ResponseEntity<Void> deleteQuestionTest(@RequestBody Map<String, Object> requestBody) {
        Object obj = requestBody.get("testrequestdto");
        TestRequestDTO testRequestDTO = null;

        if (obj != null && obj instanceof Map) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                testRequestDTO  = objectMapper.convertValue(obj, TestRequestDTO.class);
                System.out.println("Successfully converted to TestRequestDTO: " + testRequestDTO);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Unable to convert to TestRequestDTO");
            }
        } else {
            System.out.println("Object is either null or not an instance of Map");
        }
        String type = (String) requestBody.get("type");
        String testdeleteid = (String) requestBody.get("testdeleteid");
        Integer serial = (Integer) requestBody.get("serial");

        testService.deleteQuestionTest(testRequestDTO, type, testdeleteid, serial);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/test/question/add")
    public ResponseEntity<String> addQuestionTest(@RequestBody Map<String, Object> requestBody) {
        Object obj = requestBody.get("testrequestdto");
        TestRequestDTO testRequestDTO = null;

        if (obj != null && obj instanceof Map) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                testRequestDTO  = objectMapper.convertValue(obj, TestRequestDTO.class);
                System.out.println("Successfully converted to TestRequestDTO: " + testRequestDTO);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Unable to convert to TestRequestDTO");
            }
        } else {
            System.out.println("Object is either null or not an instance of Map");
        }
        String type = (String) requestBody.get("type");
        Map<String, Object> testadd = (Map<String, Object>) requestBody.get("testadd");
        String id = testService.addQuestionTest(testRequestDTO, type, testadd);

        return ResponseEntity.ok(id);
    }

    @PutMapping("/api/test/{id}")
    public ResponseEntity<TestResponseDTO> updateTest(@Valid @RequestBody TestRequestDTO dto, @PathVariable String id){
        TestResponseDTO updatedTest = testService.update(dto, id);

        return ResponseEntity.ok(updatedTest);
    }

    @DeleteMapping("/api/test/{id}")
    public ResponseEntity<Void> deleteTestById(@PathVariable String id){
        testService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
