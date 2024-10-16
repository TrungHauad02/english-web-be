package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.repository.TopicRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicService  extends BaseService<Topic, TopicDTO, TopicRepository>{

    public TopicService(TopicRepository repository) {
        super(repository);
    }

    @Override
    protected TopicDTO convertToDTO(Topic topic){
        TopicDTO dto = new TopicDTO();
        dto.setId(topic.getId());
        dto.setTitle(topic.getTitle());
        dto.setDescription(topic.getDescription());
        dto.setImage(topic.getImage());
        dto.setSerial(topic.getSerial());
        dto.setStatus(topic.getStatus());
        return dto;
    }

    @Override
    protected Topic convertToEntity(TopicDTO dto){
        Topic entity = new Topic();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
