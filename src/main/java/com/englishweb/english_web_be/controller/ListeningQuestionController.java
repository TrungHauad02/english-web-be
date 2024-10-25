package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.service.ListeningQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListeningQuestionController {
    ListeningQuestionService service;

    public ListeningQuestionController(ListeningQuestionService service) {
        this.service = service;
    }

    @GetMapping("/api/listening-question")
    public ResponseEntity<List<ListeningQuestionDTO>> findByPracticeIdId(@RequestParam String practiceId) {
        return new ResponseEntity<>(service.findByListenPracticeId(practiceId), HttpStatus.OK);
    }

    @PostMapping("/api/listening-question")
    public ResponseEntity<ListeningQuestionDTO> save(@Valid @RequestBody ListeningQuestionDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/listening-question/{id}")
    public ResponseEntity<ListeningQuestionDTO> update(@Valid @RequestBody ListeningQuestionDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto,id), HttpStatus.OK);
    }

    @DeleteMapping("/api/listening-question/{id}")
    public ResponseEntity<ListeningQuestionDTO> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}