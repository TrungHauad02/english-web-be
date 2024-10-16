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

    @GetMapping("/api/listeningQuestion")
    public ResponseEntity<List<ListeningQuestionDTO>> findByPracticeIdId(@RequestParam String practiceId) {
        List<ListeningQuestionDTO> dtoList = service.findByListenPracticeId(practiceId);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/api/listeningQuestion")
    public ResponseEntity<ListeningQuestionDTO> save(@Valid @RequestBody ListeningQuestionDTO dto) {
        ListeningQuestionDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/listeningQuestion")
    public ResponseEntity<ListeningQuestionDTO> update(@Valid @RequestBody ListeningQuestionDTO dto) {
        ListeningQuestionDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/listeningQuestion/{id}")
    public ResponseEntity<ListeningQuestionDTO> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}