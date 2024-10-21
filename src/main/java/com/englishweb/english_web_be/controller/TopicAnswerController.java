package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.service.TopicAnswerService;
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
        TopicAnswerDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/topics-answer")
    public ResponseEntity<TopicAnswerDTO> update(@Valid @RequestBody TopicAnswerDTO dto){
        TopicAnswerDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/topics-answer/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
