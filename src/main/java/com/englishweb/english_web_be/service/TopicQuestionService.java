package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.model.TopicQuestion;
import com.englishweb.english_web_be.repository.TopicQuestionRepository;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicQuestionService extends BaseService<TopicQuestion, TopicQuestionDTO, TopicQuestionRepository> {
    private final TopicService topicService;
    private final TopicAnswerService topicAnswerService;

    public TopicQuestionService(TopicQuestionRepository repository, TopicAnswerService topicAnswerService, @Lazy TopicService topicService) {
        super(repository);
        this.topicAnswerService = topicAnswerService;
        this.topicService = topicService;
    }

    public List<TopicQuestionDTO> findTopicQuestionByTopicId(String topicId) {
        ValidationUtils.getInstance().validateExistId(topicService.repository, topicId);
        List<TopicQuestion> list = repository.findAllByTopic_Id(topicId);
        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public void delete(String id){
        List<TopicAnswerDTO> answerDTOList = topicAnswerService.findAllByQuestionId(id);
        for(TopicAnswerDTO answerDTO : answerDTOList){
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
