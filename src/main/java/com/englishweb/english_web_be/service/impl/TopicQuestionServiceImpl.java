package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.model.TopicQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.TopicQuestionRepository;
import com.englishweb.english_web_be.service.TopicQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicQuestionServiceImpl extends BaseServiceImpl<TopicQuestion, TopicQuestionDTO,
        TopicQuestionRepository> implements TopicQuestionService {

    private final TopicServiceImpl topicService;
    private final TopicAnswerServiceImpl topicAnswerService;

    public TopicQuestionServiceImpl(TopicQuestionRepository repository, TopicAnswerServiceImpl topicAnswerService,
                                    @Lazy TopicServiceImpl topicService) {
        super(repository);
        this.topicAnswerService = topicAnswerService;
        this.topicService = topicService;
    }

    @Override
    public List<TopicQuestionDTO> findAllByTopicId(String topicId) {
        topicService.isExist(topicId);
        List<TopicQuestion> entityList = repository.findAllByTopic_Id(topicId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TopicQuestionDTO> findAllByTopicIdAndStatus(String topicId, StatusEnum status) {
        if(status == null)
            return findAllByTopicId(topicId);
        topicService.isExist(topicId);
        List<TopicQuestion> entityList = repository.findAllByTopic_IdAndStatus(topicId, status);
        return entityList.stream()
                .map(this::convertToDTO)
                .peek(responseDTO -> {
                    List<TopicAnswerDTO> filteredAnswers = responseDTO.getAnswers().stream()
                            .filter(answer -> answer.getStatus() == status)
                            .toList();
                    responseDTO.setAnswers(filteredAnswers);
                })
                .toList();
    }

    @Override
    public TopicQuestionDTO create(TopicQuestionDTO dto) {
        if (isSerialUnique(dto.getTopicId(), dto.getSerial(), null)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Topic ID: " + dto.getTopicId());
        }
        return super.create(dto);
    }

    @Override
    public TopicQuestionDTO update(TopicQuestionDTO dto, String id) {
        if (isSerialUnique(dto.getTopicId(), dto.getSerial(), id)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Topic ID: " + dto.getTopicId());
        }
        return super.update(dto, id);
    }

    private boolean isSerialUnique(String topicId, Integer serial, String excludeId) {
        List<TopicQuestion> entityList = repository.findAllByTopic_Id(topicId);
        return entityList.stream()
                .filter(entity -> !entity.getId().equals(excludeId))
                .anyMatch(entity -> entity.getSerial() == serial);
    }

    @Override
    public void delete(String id) {
        List<TopicAnswerDTO> answerDTOList = topicAnswerService.findAllByQuestionId(id);
        for (TopicAnswerDTO answerDTO : answerDTOList) {
            topicAnswerService.delete(answerDTO.getId());
        }
        super.delete(id);
    }

    @Override
    protected TopicQuestionDTO convertToDTO(TopicQuestion entity) {
        TopicQuestionDTO dto = new TopicQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setExplanation(entity.getExplanation());
        dto.setStatus(entity.getStatus());
        dto.setAnswers(topicAnswerService.findAllByQuestionId(entity.getId()));
        dto.setTopicId(entity.getTopic().getId());
        return dto;
    }

    @Override
    protected TopicQuestion convertToEntity(TopicQuestionDTO dto) {
        TopicQuestion entity = new TopicQuestion();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setExplanation(dto.getExplanation());
        entity.setStatus(dto.getStatus());
        entity.setTopic(topicService.convertToEntity(topicService.findById(dto.getTopicId())));
        return entity;
    }
}
