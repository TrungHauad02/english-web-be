package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.service.ListeningAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListeningAnswerController {
    ListeningAnswerService  service;

    public ListeningAnswerController(ListeningAnswerService service) {
        this.service = service;
    }

    @PostMapping("/api/listening-answer")
    public ResponseEntity<ListeningAnswerDTO> create(@Valid @RequestBody ListeningAnswerDTO dto) {
        ListeningAnswerDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/listening-answer")
    public ResponseEntity<ListeningAnswerDTO> update(@Valid @RequestBody ListeningAnswerDTO dto) {
        ListeningAnswerDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/listening-answer/{id}")
    public ResponseEntity<ListeningAnswerDTO> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
