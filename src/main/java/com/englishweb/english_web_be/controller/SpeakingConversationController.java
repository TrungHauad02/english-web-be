package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpeakingConversationController {
    SpeakingConversationService service;

    public SpeakingConversationController(SpeakingConversationService service) {
        this.service = service;
    }

    @GetMapping("/api/speakingConversation/{id}")
    public ResponseEntity<SpeakingConversationDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/api/speakingConversation")
    public ResponseEntity<List<SpeakingConversationDTO>> findBySpeakingId(@RequestParam String speakingId) {
        return new ResponseEntity<>(service.findBySpeakingId(speakingId), HttpStatus.OK);
    }

    @PostMapping("/api/speakingConversation")
    public ResponseEntity<SpeakingConversationDTO> create(@Valid @RequestBody SpeakingConversationDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/speakingConversation/{id}")
    public ResponseEntity<SpeakingConversationDTO> update(@Valid @RequestBody SpeakingConversationDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto,id), HttpStatus.OK);
    }

    @DeleteMapping("/api/speakingConversation/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}