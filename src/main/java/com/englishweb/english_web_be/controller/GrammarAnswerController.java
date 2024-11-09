package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.service.GrammarAnswerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class GrammarAnswerController {
    GrammarAnswerService service;

    @PostMapping("/api/grammar-answer")
    public ResponseEntity<GrammarAnswerDTO> create(@Valid @RequestBody GrammarAnswerDTO requestDTO) {
        return new ResponseEntity<>(service.create(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/api/grammar-answer/{id}")
    public ResponseEntity<GrammarAnswerDTO> update(
            @Valid @RequestBody GrammarAnswerDTO requestDTO,
            @PathVariable String id) {

        return new ResponseEntity<>(service.update(requestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/grammar-answer/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}