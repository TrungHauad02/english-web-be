package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.dto.request.TopicAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicAnswerResponseDTO;
import com.englishweb.english_web_be.mapper.TopicAnswerMapper;
import com.englishweb.english_web_be.model.TopicAnswer;
import com.englishweb.english_web_be.repository.TopicAnswerRepository;
import com.englishweb.english_web_be.service.TopicAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicAnswerServiceImpl extends BaseServiceImpl<TopicAnswer, TopicAnswerDTO,
        TopicAnswerRequestDTO, TopicAnswerResponseDTO, TopicAnswerMapper, TopicAnswerRepository> implements TopicAnswerService {

    private final TopicQuestionServiceImpl topicQuestionService;

    public TopicAnswerServiceImpl(TopicAnswerRepository repository,
                                  @Lazy TopicQuestionServiceImpl topicQuestionService,
                                  @Lazy TopicAnswerMapper mapper) {
        super(repository, mapper);
        this.topicQuestionService = topicQuestionService;
    }

    @Override
    public List<TopicAnswerResponseDTO> findAllByQuestionId(String questionId) {
        topicQuestionService.isExist(questionId);
        List<TopicAnswer> entityList = repository.findAllByQuestion_Id(questionId);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<TopicAnswerDTO> findAllDTOByQuestionId(String questionId) {
        topicQuestionService.isExist(questionId);
        List<TopicAnswer> entityList = repository.findAllByQuestion_Id(questionId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TopicAnswerDTO convertToDTO(TopicAnswer entity) {
        TopicAnswerDTO dto = new TopicAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCorrect(entity.isCorrect());
        dto.setStatus(entity.getStatus());
        dto.setQuestionId(entity.getQuestion().getId());
        return dto;
    }

    @Override
    protected TopicAnswer convertToEntity(TopicAnswerDTO dto) {
        TopicAnswer entity = new TopicAnswer();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setCorrect(dto.isCorrect());
        entity.setStatus(dto.getStatus());
        entity.setQuestion(topicQuestionService.convertToEntity(topicQuestionService.findDTOById(dto.getQuestionId())));
        return entity;
    }
}
