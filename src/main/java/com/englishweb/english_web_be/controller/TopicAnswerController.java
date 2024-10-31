package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.service.TopicAnswerService;
import com.englishweb.english_web_be.service.impl.TopicAnswerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopicAnswerController {

    TopicAnswerService service;

    public TopicAnswerController(TopicAnswerService service) {
        this.service = service;
    }

    @PostMapping("/api/topics-answer")
    public ResponseEntity<TopicAnswerDTO> create(@Valid @RequestBody TopicAnswerDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/topics-answer/{id}")
    public ResponseEntity<TopicAnswerDTO> update(@Valid @RequestBody TopicAnswerDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/topics-answer/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
