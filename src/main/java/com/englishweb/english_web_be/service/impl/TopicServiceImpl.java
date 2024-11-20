package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.TopicRepository;
import com.englishweb.english_web_be.repository.specifications.LessonSpecification;
import com.englishweb.english_web_be.service.TopicService;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class TopicServiceImpl extends BaseServiceImpl<Topic, TopicDTO, TopicRepository> implements TopicService {

    private final TopicQuestionServiceImpl topicQuestionService;
    private final VocabularyServiceImpl vocabularyService;
    private final FirebaseStorageServiceImpl firebaseStorageServiceImpl;

    public TopicServiceImpl(TopicRepository repository,
                            TopicQuestionServiceImpl topicQuestionService,
                            VocabularyServiceImpl vocabularyService,
                            @Lazy FirebaseStorageServiceImpl firebaseStorageServiceImpl) {
        super(repository);
        this.topicQuestionService = topicQuestionService;
        this.vocabularyService = vocabularyService;
        this.firebaseStorageServiceImpl = firebaseStorageServiceImpl;
    }

    @Override
    public Page<TopicDTO> findWithPagingSortingSearching(String title, StatusEnum status, int page, int size, String sortBy, String sortDir, Class<TopicDTO> dtoClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Topic> specTitle = Specification.where(title != null ? LessonSpecification.byTitle(title) : null);
        Specification<Topic> spec = specTitle.and(Specification.where(status != null ? LessonSpecification.hasStatus(status): null));
        log.info("Find Topic page with searching: title: {}, status: {}, pageable: {}", title, status, pageable);
        Page<TopicDTO> result = repository.findAll(spec, pageable).map(this::convertToDTO);
        log.info("Find Topic page with searching successfully: {} record found.", result.getNumberOfElements());
        return result;
    }

    @Override
    public void delete(String id) {
        isExist(id);
        List<TopicQuestionDTO> topicQuestionDTOList = topicQuestionService.findAllByTopicId(id);
        for (TopicQuestionDTO topicQuestion : topicQuestionDTOList) {
            topicQuestionService.delete(topicQuestion.getId());
        }
        List<VocabularyDTO> vocabularyDTOList = vocabularyService.findByTopicId(id);
        for (VocabularyDTO vocabulary : vocabularyDTOList) {
            vocabularyService.delete(vocabulary.getId());
        }
        try {
            firebaseStorageServiceImpl.deleteFile(findById(id).getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
