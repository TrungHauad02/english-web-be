package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.model.Vocabulary;
import com.englishweb.english_web_be.repository.VocabularyRepository;
import com.englishweb.english_web_be.service.VocabularyService;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyServiceImpl extends BaseServiceImpl<Vocabulary, VocabularyDTO, VocabularyRepository> implements VocabularyService {
    private final TopicServiceImpl topicService;

    public VocabularyServiceImpl(VocabularyRepository repository, @Lazy TopicServiceImpl topicService){
        super(repository);
        this.topicService = topicService;
    }

    public Page<VocabularyDTO> findByPageTopicId(int page, int size, String sortBy, String sortDir, Class<VocabularyDTO> dtoClass, String topicId){
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        topicService.isExist(topicId);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Vocabulary> entityPage = repository.findAllByTopic_Id(pageable, topicId);
        return entityPage.map(this::convertToDTO);
    }

    public List<VocabularyDTO> findByTopicId(String topicId){
        topicService.isExist(topicId);
        return repository.findAllByTopic_Id(topicId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected VocabularyDTO convertToDTO(Vocabulary entity){
        VocabularyDTO dto = new VocabularyDTO();
        dto.setId(entity.getId());
        dto.setWord(entity.getWord());
        dto.setMeaning(entity.getMeaning());
        dto.setWordType(entity.getWordType());
        dto.setPhonetic(entity.getPhonetic());
        dto.setStatus(entity.getStatus());
        dto.setImage(entity.getImage());
        dto.setExample(entity.getExample());
        dto.setTopicId(entity.getTopic().getId());
        return dto;
    }

    @Override
    protected Vocabulary convertToEntity(VocabularyDTO dto){
        Vocabulary entity = new Vocabulary();
        entity.setId(dto.getId());
        entity.setWord(dto.getWord());
        entity.setMeaning(dto.getMeaning());
        entity.setWordType(dto.getWordType());
        entity.setPhonetic(dto.getPhonetic());
        entity.setStatus(dto.getStatus());
        entity.setImage(dto.getImage());
        entity.setExample(dto.getExample());
        entity.setTopic(topicService.convertToEntity(topicService.findById(dto.getTopicId())));
        return entity;
    }
}
