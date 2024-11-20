package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.WritingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/writings")
@Slf4j
@Tag(name = "Writing Controller")
public class WritingController {
    private final WritingService service;

    public WritingController(WritingService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get paginated list of writings",
            description = "Send a request via this API to get a paginated list of writings")
    @GetMapping
    public ResponseEntity<Page<WritingDTO>> findByPage(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                       @RequestParam(defaultValue = "asc") String sortDir,
                                                       @RequestParam(required = false) String title,
                                                       @RequestParam(required = false) StatusEnum status) {
        return new ResponseEntity<>(service.findWithPagingSortingSearching(title, status, page, size, sortBy, sortDir, WritingDTO.class), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get writing by ID",
            description = "Send a request via this API to get a specific writing by ID")
    @GetMapping("/{id}")
    public ResponseEntity<WritingDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new writing",
            description = "Send a request via this API to create a new writing")
    @PostMapping
    public ResponseEntity<WritingDTO> create(@Valid @RequestBody WritingDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update writing",
            description = "Send a request via this API to update an existing writing")
    @PutMapping("/{id}")
    public ResponseEntity<WritingDTO> update(@Valid @RequestBody WritingDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete writing",
            description = "Send a request via this API to delete an existing writing")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
