package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.TopicRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
@Slf4j
@Tag(name = "Topic Controller")
public class TopicController {
    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get paginated list of topics",
            description = "Send a request via this API to get a paginated list of topics")
    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> findByPage(@RequestParam int page,
                                                             @RequestParam int size,
                                                             @RequestParam(defaultValue = "id") String sortBy,
                                                             @RequestParam(defaultValue = "asc") String sortDir,
                                                             @RequestParam(required = false) StatusEnum status) {
        return new ResponseEntity<>(service.findTopicWithStatusAndPagingAndSorting(status,page, size, sortBy, sortDir, TopicResponseDTO.class), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get topic by ID",
            description = "Send a request via this API to get a specific topic by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new topic",
            description = "Send a request via this API to create a new topic")
    @PostMapping
    public ResponseEntity<TopicResponseDTO> create(@Valid @RequestBody TopicRequestDTO topicDTO) {
        return new ResponseEntity<>(service.create(topicDTO), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update topic",
            description = "Send a request via this API to update an existing topic")
    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> update(@Valid @RequestBody TopicRequestDTO topicDTO, @PathVariable String id) {
        return new ResponseEntity<>(service.update(topicDTO, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete topic",
            description = "Send a request via this API to delete an existing topic")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
