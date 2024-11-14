package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SubmitTestListeningAnswerDTO;
import com.englishweb.english_web_be.service.impl.SubmitTestListeningAnswerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submit-test-listening-answers")
@Slf4j
@Tag(name = "SubmitTestListeningAnswer Controller")
public class SubmitTestListeningAnswerController {

    private final SubmitTestListeningAnswerServiceImpl service;

    public SubmitTestListeningAnswerController(SubmitTestListeningAnswerServiceImpl service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get submit test listening answer by id", description = "Send a request via this API to get submit test listening answer information")
    @GetMapping("/{id}")
    public ResponseEntity<SubmitTestListeningAnswerDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new submit test listening answer", description = "Send a request via this API to create submit test listening answer")
    @PostMapping
    public ResponseEntity<SubmitTestListeningAnswerDTO> create(@Valid @RequestBody SubmitTestListeningAnswerDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update submit test listening answer", description = "Send a request via this API to update submit test listening answer")
    @PutMapping("/{id}")
    public ResponseEntity<SubmitTestListeningAnswerDTO> update(@Valid @RequestBody SubmitTestListeningAnswerDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete submit test listening answer", description = "Send a request via this API to delete submit test listening answer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
