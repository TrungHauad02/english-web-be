package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.repository.TopicRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {
    TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public Page<TopicDTO> retrieveTopicsByPage(int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by("serial"));
        Page<Topic> entityPages = repository.findAllTopics(pageable);
        return entityPages.map(this::convertToDTO);
    }

    public TopicDTO retrieveTopicById(String id){
        return convertToDTO(repository.findById(id).get());
    }


    private TopicDTO convertToDTO(Topic topic){
        TopicDTO dto = new TopicDTO();
        dto.setId(topic.getId());
        dto.setTitle(topic.getTitle());
        dto.setDescription(topic.getDescription());
        dto.setImage(topic.getImage());
        dto.setSerial(topic.getSerial());
        dto.setStatus(topic.getStatus());
        return dto;
    }
}
