package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.service.ReadingAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReadingAnswerController {
    ReadingAnswerService service;

    public ReadingAnswerController(ReadingAnswerService service) {
        this.service = service;
    }

    @PostMapping("/api/reading-answer")
    public ResponseEntity<ReadingAnswerDTO> create(@Valid @RequestBody ReadingAnswerDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PutMapping("/api/reading-answer")
    public ResponseEntity<ReadingAnswerDTO> update(@Valid @RequestBody ReadingAnswerDTO dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/api/reading-answer/{id}")
    public ResponseEntity<ReadingAnswerDTO> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
