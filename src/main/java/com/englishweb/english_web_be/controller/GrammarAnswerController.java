package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.GrammarAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarAnswerResponseDTO;
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
    public ResponseEntity<GrammarAnswerResponseDTO> create(@Valid @RequestBody GrammarAnswerRequestDTO requestDTO) {
        return new ResponseEntity<>(service.create(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/api/grammar-answer/{id}")
    public ResponseEntity<GrammarAnswerResponseDTO> update(
            @Valid @RequestBody GrammarAnswerRequestDTO requestDTO,
            @PathVariable String id) {

        return new ResponseEntity<>(service.update(requestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/grammar-answer/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}