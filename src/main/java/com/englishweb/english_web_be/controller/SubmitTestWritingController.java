package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SubmitTestWritingDTO;
import com.englishweb.english_web_be.service.impl.SubmitTestWritingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submit-test-writings")
@Slf4j
@Tag(name = "SubmitTestWriting Controller")
public class SubmitTestWritingController {

    private final SubmitTestWritingServiceImpl service;

    public SubmitTestWritingController(SubmitTestWritingServiceImpl service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get submit test writing by id", description = "Send a request via this API to get submit test writing information")
    @GetMapping("/{id}")
    public ResponseEntity<SubmitTestWritingDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new submit test writing", description = "Send a request via this API to create submit test writing")
    @PostMapping
    public ResponseEntity<SubmitTestWritingDTO> create(@Valid @RequestBody SubmitTestWritingDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update submit test writing", description = "Send a request via this API to update submit test writing")
    @PutMapping("/{id}")
    public ResponseEntity<SubmitTestWritingDTO> update(@Valid @RequestBody SubmitTestWritingDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete submit test writing", description = "Send a request via this API to delete submit test writing")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
