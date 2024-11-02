package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.SpeakingRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingResponseDTO;
import com.englishweb.english_web_be.service.SpeakingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/speakings")
@Slf4j
@Tag(name = "Speaking Controller")
public class SpeakingController {
    private final SpeakingService service;

    public SpeakingController(SpeakingService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get paginated list of speakings",
            description = "Send a request via this API to get a paginated list of speaking questions")
    @GetMapping
    public ResponseEntity<Page<SpeakingResponseDTO>> findByPage(@RequestParam int page,
                                                                @RequestParam int size,
                                                                @RequestParam(defaultValue = "id") String sortBy,
                                                                @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir, SpeakingResponseDTO.class), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get speaking by ID",
            description = "Send a request via this API to get a specific speaking question by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SpeakingResponseDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new speaking question",
            description = "Send a request via this API to create a new speaking question")
    @PostMapping
    public ResponseEntity<SpeakingResponseDTO> create(@Valid @RequestBody SpeakingRequestDTO dto) {
        SpeakingResponseDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update speaking question",
            description = "Send a request via this API to update an existing speaking question")
    @PutMapping("/{id}")
    public ResponseEntity<SpeakingResponseDTO> update(@Valid @RequestBody SpeakingRequestDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete speaking question",
            description = "Send a request via this API to delete an existing speaking question")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
