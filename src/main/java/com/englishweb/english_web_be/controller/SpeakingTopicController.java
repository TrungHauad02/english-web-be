package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SpeakingTopicDTO;
import com.englishweb.english_web_be.service.SpeakingTopicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpeakingTopicController {
    SpeakingTopicService service;

    public SpeakingTopicController(SpeakingTopicService service) {
        this.service = service;
    }

    @GetMapping("/api/speakingTopic/{id}")
    public ResponseEntity<SpeakingTopicDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/api/speakingTopic")
    public ResponseEntity<SpeakingTopicDTO> findBySpeakingId(@RequestParam String speakingId) {
        return new ResponseEntity<>(service.findBySpeakingId(speakingId), HttpStatus.OK);
    }

    @PostMapping("/api/speakingTopic")
    public ResponseEntity<SpeakingTopicDTO> create(@Valid @RequestBody SpeakingTopicDTO dto) {
        SpeakingTopicDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/speakingTopic")
    public ResponseEntity<SpeakingTopicDTO> update(@Valid @RequestBody SpeakingTopicDTO dto) {
        SpeakingTopicDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/speakingTopic/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
