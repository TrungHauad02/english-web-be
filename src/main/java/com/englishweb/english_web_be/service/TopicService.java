package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.repository.TopicRepository;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public Page<TopicDTO> retrieveTopicsByPage(int page, int size, String sortBy, String sortDir){
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, TopicDTO.class);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Topic> entityPage = repository.findAllTopics(pageable);
        return entityPage.map(this::convertToDTO);
    }

    public TopicDTO retrieveTopicById(String id){
        return convertToDTO(repository.findById(id).get());
    }

    public TopicDTO createTopic(TopicDTO dto){
        Topic entity = convertToEntity(dto);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    public TopicDTO updateTopic(TopicDTO dto){
        Topic entity = convertToEntity(dto);
        entity.setId(dto.getId());
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    public boolean deleteTopic(String id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private Topic convertToEntity(TopicDTO dto){
        Topic entity = new Topic();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        return entity;
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
