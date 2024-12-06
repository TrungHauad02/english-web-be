package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SubmitTestSpeakingDTO;
import com.englishweb.english_web_be.model.SubmitTestSpeaking;
import com.englishweb.english_web_be.repository.SubmitTestSpeakingRepository;
import com.englishweb.english_web_be.service.SubmitTestSpeakingService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmitTestSpeakingServiceImpl extends BaseServiceImpl<SubmitTestSpeaking, SubmitTestSpeakingDTO, SubmitTestSpeakingRepository>
        implements SubmitTestSpeakingService {

    private final SubmitTestServiceImpl submitTestService;
    private final TestSpeakingQuestionServiceImpl testSpeakingQuestionService;

    public SubmitTestSpeakingServiceImpl(SubmitTestSpeakingRepository repository,
                                         @Lazy SubmitTestServiceImpl submitTestService,
                                         @Lazy TestSpeakingQuestionServiceImpl testSpeakingQuestionService) {
        super(repository);
        this.submitTestService = submitTestService;
        this.testSpeakingQuestionService = testSpeakingQuestionService;
    }

    public List<SubmitTestSpeakingDTO> findAllBySubmitTestId(String submitTestId) {
        List<SubmitTestSpeaking> entities = repository.findAllBySubmitTest_Id(submitTestId);
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    protected SubmitTestSpeaking convertToEntity(SubmitTestSpeakingDTO dto) {
        SubmitTestSpeaking entity = new SubmitTestSpeaking();
        entity.setId(dto.getId());
        entity.setSubmitTest(submitTestService.convertToEntity(submitTestService.findById(dto.getSubmitTestId())));
        entity.setTestSpeakingQuestion(testSpeakingQuestionService.convertToEntity(testSpeakingQuestionService.findById(dto.getTestSpeakingQuestionId())));
        entity.setScore(dto.getScore());
        entity.setContent(dto.getContent());
        entity.setTranscript(dto.getTranscript());
        entity.setComment(dto.getComment());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected SubmitTestSpeakingDTO convertToDTO(SubmitTestSpeaking entity) {
        SubmitTestSpeakingDTO dto = new SubmitTestSpeakingDTO();
        dto.setId(entity.getId());
        dto.setSubmitTestId(entity.getSubmitTest().getId());
        dto.setTestSpeakingQuestionId(entity.getTestSpeakingQuestion().getId());
        dto.setScore(entity.getScore());
        dto.setContent(entity.getContent());
        dto.setTranscript(entity.getTranscript());
        dto.setComment(entity.getComment());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
