package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.dto.request.TopicQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicAnswerResponseDTO;
import com.englishweb.english_web_be.dto.response.TopicQuestionResponseDTO;
import com.englishweb.english_web_be.mapper.TopicQuestionMapper;
import com.englishweb.english_web_be.model.TopicQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.TopicQuestionRepository;
import com.englishweb.english_web_be.service.TopicQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicQuestionServiceImpl extends BaseServiceImpl<TopicQuestion, TopicQuestionDTO,
        TopicQuestionRequestDTO, TopicQuestionResponseDTO, TopicQuestionMapper,
        TopicQuestionRepository> implements TopicQuestionService {

    private final TopicServiceImpl topicService;
    private final TopicAnswerServiceImpl topicAnswerService;

    public TopicQuestionServiceImpl(TopicQuestionRepository repository, TopicAnswerServiceImpl topicAnswerService,
                                    @Lazy TopicServiceImpl topicService,
                                    @Lazy TopicQuestionMapper mapper) {
        super(repository, mapper);
        this.topicAnswerService = topicAnswerService;
        this.topicService = topicService;
    }

    @Override
    public List<TopicQuestionResponseDTO> findAllByTopicId(String topicId) {
        topicService.isExist(topicId);
        List<TopicQuestion> entityList = repository.findAllByTopic_Id(topicId);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<TopicQuestionResponseDTO> findAllByTopicIdAndStatus(String topicId, StatusEnum status) {
        if(status == null)
            return findAllByTopicId(topicId);
        topicService.isExist(topicId);
        List<TopicQuestion> entityList = repository.findAllByTopic_IdAndStatus(topicId, status);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .peek(responseDTO -> {
                    List<TopicAnswerResponseDTO> filteredAnswers = responseDTO.getAnswers().stream()
                            .filter(answer -> answer.getStatus() == status)
                            .toList();
                    responseDTO.setAnswers(filteredAnswers);
                })
                .toList();
    }

    @Override
    public List<TopicQuestionDTO> findAllDTOByTopicId(String topicId) {
        topicService.isExist(topicId);
        List<TopicQuestion> entityList = repository.findAllByTopic_Id(topicId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public void delete(String id) {
        List<TopicAnswerDTO> answerDTOList = topicAnswerService.findAllDTOByQuestionId(id);
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
        dto.setAnswers(topicAnswerService.findAllDTOByQuestionId(entity.getId()));
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
        entity.setTopic(topicService.convertToEntity(topicService.findDTOById(dto.getTopicId())));
        return entity;
    }
}
