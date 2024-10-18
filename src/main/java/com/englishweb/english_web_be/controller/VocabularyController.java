package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.service.VocabularyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VocabularyController {

    VocabularyService service;

    public VocabularyController(VocabularyService service) {
        this.service = service;
    }

    @GetMapping("/api/vocabulary")
    public ResponseEntity<Page<VocabularyDTO>> findByPage(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                       @RequestParam(defaultValue = "asc") String sortDir){
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir, VocabularyDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/topics/{topicId}/vocabulary")
    public ResponseEntity<Page<VocabularyDTO>> findByPageAndTopicId(@RequestParam int page,
                                                          @RequestParam int size,
                                                          @RequestParam(defaultValue = "id") String sortBy,
                                                          @RequestParam(defaultValue = "asc") String sortDir,
                                                          @PathVariable String topicId){
        return new ResponseEntity<>(service.findByPageTopicId(page, size, sortBy, sortDir, VocabularyDTO.class, topicId), HttpStatus.OK);
    }

    @GetMapping("/api/vocabulary/topics/{topicId}")
    public ResponseEntity<List<VocabularyDTO>> findByTopicId(@PathVariable String topicId){
        return new ResponseEntity<>(service.findByTopicId(topicId), HttpStatus.OK);
    }

    @GetMapping("/api/vocabulary/{id}")
    public ResponseEntity<VocabularyDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/vocabulary")
    public ResponseEntity<VocabularyDTO> create(@Valid @RequestBody VocabularyDTO dto){
        VocabularyDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/vocabulary")
    public ResponseEntity<VocabularyDTO> update(@Valid @RequestBody VocabularyDTO dto){
        VocabularyDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/vocabulary/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
