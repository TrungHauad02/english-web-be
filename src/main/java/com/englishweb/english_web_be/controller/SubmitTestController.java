package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SubmitTestDTO;
import com.englishweb.english_web_be.dto.TestDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.SubmitTestRepository;
import com.englishweb.english_web_be.service.impl.SubmitTestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/submit-test")
@Slf4j
@Tag(name = "SubmitTest Controller")
public class SubmitTestController {

    private final SubmitTestServiceImpl service;
    private final SubmitTestServiceImpl submitTestServiceImpl;

    public SubmitTestController(SubmitTestServiceImpl service, SubmitTestRepository submitTestRepository, SubmitTestServiceImpl submitTestServiceImpl) {
        this.service = service;
        this.submitTestServiceImpl = submitTestServiceImpl;
    }

    @Operation(method = "GET", summary = "Retrieve or search submitTests", description = "Retrieve all submitTests or search with optional filters")


    @GetMapping
    public ResponseEntity<Page<SubmitTestDTO>> getTests(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) TestTypeEnum type,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) StatusEnum status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<SubmitTestDTO> result = submitTestServiceImpl.findSubmitTestsBySpecification(title, type, page, size, startDate, endDate,userId,status);
        return ResponseEntity.ok(result);
    }


    @Operation(method = "GET", summary = "Get submit test by id", description = "Send a request via this API to get submit test information")
    @GetMapping("/{id}")
    public ResponseEntity<SubmitTestDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new submit test", description = "Send a request via this API to create submit test")
    @PostMapping
    public ResponseEntity<SubmitTestDTO> create(@Valid @RequestBody SubmitTestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update submit test", description = "Send a request via this API to update submit test")
    @PutMapping("/{id}")
    public ResponseEntity<SubmitTestDTO> update(@Valid @RequestBody SubmitTestDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete submit test", description = "Send a request via this API to delete submit test")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
