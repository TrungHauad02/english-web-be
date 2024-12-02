package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.VocabularyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vocabulary")
@Slf4j
@Tag(name = "Vocabulary Controller")
public class VocabularyController {
    private final VocabularyService service;

    public VocabularyController(VocabularyService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get paginated list of vocabulary by topic ID",
            description = "Send a request via this API to get a paginated list of vocabulary by topic ID")
    @GetMapping
    public ResponseEntity<Page<VocabularyDTO>> findByPageAndTopicId(@RequestParam int page,
                                                                    @RequestParam int size,
                                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                                    @RequestParam(defaultValue = "asc") String sortDir,
                                                                    @RequestParam(required = false) StatusEnum status,
                                                                    @RequestParam(required = false) String searchText,
                                                                    @RequestParam String topicId) {
        return new ResponseEntity<>(service.findByPageAndStatusAndTopicId(status, page, size, sortBy, sortDir, VocabularyDTO.class, topicId, searchText), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get vocabulary by topic ID",
            description = "Send a request via this API to get all vocabulary for a specific topic ID")
    @GetMapping("/topics/{topicId}")
    public ResponseEntity<List<VocabularyDTO>> findByTopicId(@PathVariable String topicId) {
        return new ResponseEntity<>(service.findByTopicId(topicId), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get vocabulary by ID",
            description = "Send a request via this API to get a specific vocabulary by ID")
    @GetMapping("/{id}")
    public ResponseEntity<VocabularyDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new vocabulary",
            description = "Send a request via this API to create a new vocabulary")
    @PostMapping
    public ResponseEntity<VocabularyDTO> create(@Valid @RequestBody VocabularyDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update vocabulary",
            description = "Send a request via this API to update an existing vocabulary")
    @PutMapping("/{id}")
    public ResponseEntity<VocabularyDTO> update(@Valid @RequestBody VocabularyDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete vocabulary",
            description = "Send a request via this API to delete an existing vocabulary")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
