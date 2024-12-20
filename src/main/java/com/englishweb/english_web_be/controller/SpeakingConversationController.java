package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponse;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import com.englishweb.english_web_be.service.impl.SpeakingConversationServiceImpl;
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

    public SpeakingConversationController(SpeakingConversationServiceImpl service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get speaking conversation by ID",
            description = "Send a request via this API to get a specific speaking conversation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SpeakingConversationResponse> findById(@PathVariable String id) {
        SpeakingConversationDTO result = service.findById(id);
        SpeakingConversationResponse response = SpeakingConversationResponse.builder()
                .id(result.getId())
                .name(result.getName())
                .serial(result.getSerial())
                .content(result.getContent())
                .status(result.getStatus())
                .speakingId(result.getSpeakingId())
                .audioUrl(service.getAudio(result))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get speaking conversations by speaking ID",
            description = "Send a request via this API to get all conversations for a specific speaking")
    @GetMapping
    public ResponseEntity<List<SpeakingConversationResponse>> findBySpeakingId(
            @RequestParam String speakingId,
            @RequestParam(required = false) StatusEnum status) {
        List<SpeakingConversationDTO> dtos = service.findBySpeakingIdAndStatus(speakingId, status);

        List<SpeakingConversationResponse> responses = dtos.stream()
                .map(dto -> SpeakingConversationResponse.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .serial(dto.getSerial())
                        .content(dto.getContent())
                        .status(dto.getStatus())
                        .speakingId(dto.getSpeakingId())
                        .audioUrl(service.getAudio(dto))
                        .build())
                .toList();

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new speaking conversation",
            description = "Send a request via this API to create a new speaking conversation")
    @PostMapping
    public ResponseEntity<SpeakingConversationResponse> create(@Valid @RequestBody SpeakingConversationDTO dto) {
        // Create conversation
        SpeakingConversationDTO created = service.create(dto);
        // Save audio and return response
        return new ResponseEntity<>(service.saveAudio(created), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update speaking conversation",
            description = "Send a request via this API to update an existing speaking conversation")
    @PutMapping("/{id}")
    public ResponseEntity<SpeakingConversationResponse> update(@Valid @RequestBody SpeakingConversationDTO dto, @PathVariable String id) {
        SpeakingConversationDTO beforeUpdate = service.findById(id);
        SpeakingConversationDTO updated = service.update(dto, id);
        String audio = service.getAudio(beforeUpdate);
        if (!beforeUpdate.getContent().equals(updated.getContent()) || !beforeUpdate.getName().equals(updated.getName())) {
            service.deleteAudio(beforeUpdate);
            audio = service.saveAudio(updated).getAudioUrl();
        }
        SpeakingConversationResponse response = SpeakingConversationResponse.builder()
                .id(updated.getId())
                .name(updated.getName())
                .serial(updated.getSerial())
                .content(updated.getContent())
                .status(updated.getStatus())
                .speakingId(updated.getSpeakingId())
                .audioUrl(audio)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete speaking conversation",
            description = "Send a request via this API to delete an existing speaking conversation")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
