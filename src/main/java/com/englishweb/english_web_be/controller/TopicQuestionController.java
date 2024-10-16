package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.service.TopicQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicQuestionController {

    TopicQuestionService service;

    public TopicQuestionController(TopicQuestionService service) {
        this.service = service;
    }

    @GetMapping("/api/topics-question")
    public List<TopicQuestionDTO> findAllQuestionByTopicId_1(@RequestParam String topicId) {
        return service.findTopicQuestionByTopicId(topicId);
    }

    @GetMapping("/api/topics/{topicId}/topics-question")
    public List<TopicQuestionDTO> findAllQuestionByTopicId_2(@PathVariable String topicId) {
        return service.findTopicQuestionByTopicId(topicId);
    }


}
