package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.service.SpeakingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpeakingController {
    SpeakingService service;

    public SpeakingController(SpeakingService service) {
        this.service = service;
    }

    @GetMapping("/api/speakings")
    public ResponseEntity<Page<SpeakingDTO>> findByPage(@RequestParam int page,
                                                        @RequestParam int size,
                                                        @RequestParam(defaultValue = "id") String sortBy,
                                                        @RequestParam(defaultValue = "asc") String sortDir){
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir, SpeakingDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/speakings/{id}")
    public ResponseEntity<SpeakingDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/speakings")
    public ResponseEntity<SpeakingDTO> create(@Valid @RequestBody SpeakingDTO dto){
        SpeakingDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/speakings")
    public ResponseEntity<SpeakingDTO> update(@Valid @RequestBody SpeakingDTO dto){
        SpeakingDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/speakings/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
