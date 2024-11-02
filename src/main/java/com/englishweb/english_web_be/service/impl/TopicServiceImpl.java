package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.dto.request.TopicRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicResponseDTO;
import com.englishweb.english_web_be.mapper.TopicMapper;
import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.repository.TopicRepository;
import com.englishweb.english_web_be.service.TopicService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl extends BaseServiceImpl<Topic, TopicDTO, TopicRequestDTO, TopicResponseDTO, TopicMapper, TopicRepository> implements TopicService {

    private final TopicQuestionServiceImpl topicQuestionService;
    private final VocabularyServiceImpl vocabularyService;

    public TopicServiceImpl(TopicRepository repository,
                            TopicQuestionServiceImpl topicQuestionService,
                            VocabularyServiceImpl vocabularyService,
                            @Lazy TopicMapper mapper) {
        super(repository, mapper);
        this.topicQuestionService = topicQuestionService;
        this.vocabularyService = vocabularyService;
    }

    @Override
    public void delete(String id) {
        List<TopicQuestionDTO> topicQuestionDTOList = topicQuestionService.findAllDTOByTopicId(id);
        for (TopicQuestionDTO topicQuestion : topicQuestionDTOList) {
            topicQuestionService.delete(topicQuestion.getId());
        }
        List<VocabularyDTO> vocabularyDTOList = vocabularyService.findDTOByTopicId(id);
        for (VocabularyDTO vocabulary : vocabularyDTOList) {
            vocabularyService.delete(vocabulary.getId());
        }
        super.delete(id);
    }

    @Override
    protected TopicDTO convertToDTO(Topic entity) {
        TopicDTO dto = new TopicDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    protected Topic convertToEntity(TopicDTO dto) {
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
