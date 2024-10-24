package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.service.ReadingQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReadingQuestionController {
    ReadingQuestionService service;

    public ReadingQuestionController(ReadingQuestionService service) {
        this.service = service;
    }

    @GetMapping("/api/reading-question")
    public ResponseEntity<List<ReadingQuestionDTO>> findByReadingId(@RequestParam String readingId) {
        return new ResponseEntity<>(service.findAllByReadingId(readingId), HttpStatus.OK);
    }

    @PostMapping("/api/reading-question")
    public ResponseEntity<ReadingQuestionDTO> create(@Valid @RequestBody ReadingQuestionDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PutMapping("/api/reading-question")
    public ResponseEntity<ReadingQuestionDTO> update(@Valid @RequestBody ReadingQuestionDTO dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/api/reading-question/{id}")
    public ResponseEntity<ReadingQuestionDTO> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
