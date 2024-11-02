package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.ReadingRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.ReadingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readings")
@Slf4j
@Tag(name = "Reading Controller")
public class ReadingController {
    ReadingService service;

    public ReadingController(ReadingService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get reading with status, sorting and pagination",
            description = "Send a request via this API to get reading with status, sorting and pagination. Status is optional")
    @GetMapping
    public ResponseEntity<Page<ReadingResponseDTO>> findReadingWithStatusAndPagingAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) StatusEnum status) {
        return new ResponseEntity<>(
                service.findByPage(page, size, sortBy, sortDir, ReadingResponseDTO.class),
                HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get reading by id",
            description = "Send a request via this API to get reading information")
    @GetMapping("/{id}")
    public ResponseEntity<ReadingResponseDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new reading",
            description = "Send a request via this API to create reading")
    @PostMapping
    public ResponseEntity<ReadingResponseDTO> create(@Valid @RequestBody ReadingRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update reading",
            description = "Send a request via this API to update reading")
    @PutMapping("/{id}")
    public ResponseEntity<ReadingResponseDTO> update(@Valid @RequestBody ReadingRequestDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete reading",
            description = "Send a request via this API to delete reading")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}