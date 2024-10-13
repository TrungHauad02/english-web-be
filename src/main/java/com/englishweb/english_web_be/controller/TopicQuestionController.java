package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.service.TopicQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicQuestionController {

    TopicQuestionService service;

    public TopicQuestionController(TopicQuestionService service) {
        this.service = service;
    }

    @GetMapping("/topics-question")
    public List<TopicQuestionDTO> retrieveQuestionByTopicId(@RequestParam String topicId) {
        return service.retrieveTopicQuestionByTopicId(topicId);
    }
}
