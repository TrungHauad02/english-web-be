package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.service.GrammarAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GrammarAnswerController {
    GrammarAnswerService service;

    public GrammarAnswerController(GrammarAnswerService service) {
        this.service = service;
    }

    @PostMapping("/api/grammar-answer")
    public ResponseEntity<GrammarAnswerDTO> create(@Valid @RequestBody GrammarAnswerDTO dto) {
        GrammarAnswerDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/grammar-answer")
    public ResponseEntity<GrammarAnswerDTO> update(@Valid @RequestBody GrammarAnswerDTO dto){
        GrammarAnswerDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/grammar-answer/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}