package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.SpeakingConversationRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speakingConversation")
@Slf4j
@Tag(name = "Speaking Conversation Controller")
public class SpeakingConversationController {
    private final SpeakingConversationService service;

    public SpeakingConversationController(SpeakingConversationService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get speaking conversation by ID",
            description = "Send a request via this API to get a specific speaking conversation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SpeakingConversationResponseDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get speaking conversations by speaking ID",
            description = "Send a request via this API to get all conversations for a specific speaking")
    @GetMapping
    public ResponseEntity<List<SpeakingConversationResponseDTO>> findBySpeakingId(
            @RequestParam String speakingId,
            @RequestParam(required = false) StatusEnum status) {
        return new ResponseEntity<>(service.findBySpeakingIdAndStatus(speakingId, status), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new speaking conversation",
            description = "Send a request via this API to create a new speaking conversation")
    @PostMapping
    public ResponseEntity<SpeakingConversationResponseDTO> create(@Valid @RequestBody SpeakingConversationRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update speaking conversation",
            description = "Send a request via this API to update an existing speaking conversation")
    @PutMapping("/{id}")
    public ResponseEntity<SpeakingConversationResponseDTO> update(@Valid @RequestBody SpeakingConversationRequestDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete speaking conversation",
            description = "Send a request via this API to delete an existing speaking conversation")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
