package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.TopicQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics-question")
@Slf4j
@Tag(name = "Topic Question Controller")
public class TopicQuestionController {
    private final TopicQuestionService service;

    public TopicQuestionController(TopicQuestionService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get all questions by topic ID",
            description = "Send a request via this API to get all questions for a specific topic")
    @GetMapping
    public ResponseEntity<List<TopicQuestionDTO>> findAllQuestionByTopicId(
            @RequestParam String topicId,
            @RequestParam(required = false) StatusEnum status) {
        return new ResponseEntity<>(service.findAllByTopicIdAndStatus(topicId, status), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new topic question",
            description = "Send a request via this API to create a new topic question")
    @PostMapping
    public ResponseEntity<TopicQuestionDTO> create(@Valid @RequestBody TopicQuestionDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update topic question",
            description = "Send a request via this API to update an existing topic question")
    @PutMapping("/{id}")
    public ResponseEntity<TopicQuestionDTO> update(@Valid @RequestBody TopicQuestionDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete topic question",
            description = "Send a request via this API to delete an existing topic question")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
