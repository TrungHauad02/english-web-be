package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.dto.TestDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.service.TestService;
import com.mysql.cj.log.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/test")
@Tag(name = "Test Controller")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Operation(method = "GET", summary = "Retrieve or search tests", description = "Retrieve all tests or search with optional filters")
    @GetMapping
    public ResponseEntity<Page<TestDTO>> getTests(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) TestTypeEnum type,
            @RequestParam(required = false) StatusEnum status,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String sortDirection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<TestDTO> result = testService.findTestsBySpecification(title, type, page, size,status,userId,sortDirection);
        return ResponseEntity.ok(result);
    }
    @Operation(method = "GET", summary = "get test by ID And Status" , description = "Retrieve specific test by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> getByTestIdAndStatus(
            @PathVariable String id,
            @RequestParam(required = false) StatusEnum status) {
        TestDTO result = testService.findByIdAndStatus(id, status);
        return ResponseEntity.ok(result);
    }

    @Operation(method = "POST", summary = "Create a new test", description = "Create a new test entry")
    @PostMapping
    public ResponseEntity<TestDTO> createTest(@Valid @RequestBody TestDTO dto) {
        TestDTO createdTest = testService.create(dto);
        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update test by ID", description = "Update existing test by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> updateTest(@Valid @RequestBody TestDTO dto, @PathVariable String id) {
        TestDTO updatedTest = testService.update(dto, id);
        return ResponseEntity.ok(updatedTest);
    }

    @Operation(method = "DELETE", summary = "Delete test by ID", description = "Delete specific test by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestById(@PathVariable String id) {
        testService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable String id) {
        boolean updated = testService.updateStatus(id);

        if (updated) {
            return ResponseEntity.ok("Status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update status.");
        }
    }
    @GetMapping("/max-serial")
    public ResponseEntity<Integer> getMaxSerial() {
        Integer maxSerial = testService.getMaxSerial();

        if (maxSerial != null) {
            return ResponseEntity.ok(maxSerial);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @DeleteMapping("/delete-submit-tests/{id}")
    public ResponseEntity<Void> deleteSubmitTestsById(@PathVariable String id) {
        testService.deleteSubmitTestsById(id);
        return ResponseEntity.noContent().build();
    }
}
