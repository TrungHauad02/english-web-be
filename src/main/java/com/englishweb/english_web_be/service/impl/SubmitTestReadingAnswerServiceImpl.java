package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SubmitTestReadingAnswerDTO;
import com.englishweb.english_web_be.model.SubmitTestReadingAnswer;
import com.englishweb.english_web_be.repository.SubmitTestReadingAnswerRepository;
import com.englishweb.english_web_be.service.SubmitTestReadingAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmitTestReadingAnswerServiceImpl extends BaseServiceImpl<SubmitTestReadingAnswer, SubmitTestReadingAnswerDTO, SubmitTestReadingAnswerRepository>
        implements SubmitTestReadingAnswerService {

    private final SubmitTestServiceImpl submitTestService;
    private final TestReadingQuestionServiceImpl testReadingQuestionService;
    private final TestReadingAnswerServiceImpl testReadingAnswerService;

    public SubmitTestReadingAnswerServiceImpl(SubmitTestReadingAnswerRepository repository,
                                              @Lazy SubmitTestServiceImpl submitTestService,
                                              @Lazy TestReadingQuestionServiceImpl testReadingQuestionService,
                                              @Lazy TestReadingAnswerServiceImpl testReadingAnswerService) {
        super(repository);
        this.submitTestService = submitTestService;
        this.testReadingQuestionService = testReadingQuestionService;
        this.testReadingAnswerService = testReadingAnswerService;
    }

    public List<SubmitTestReadingAnswerDTO> findAllBySubmitTestId(String submitTestId) {
        List<SubmitTestReadingAnswer> entities = repository.findAllBySubmitTest_Id(submitTestId);
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    protected SubmitTestReadingAnswer convertToEntity(SubmitTestReadingAnswerDTO dto) {
        SubmitTestReadingAnswer entity = new SubmitTestReadingAnswer();
        entity.setId(dto.getId());
        entity.setSubmitTest(submitTestService.convertToEntity(submitTestService.findById(dto.getSubmitTestId())));
        entity.setQuestion(testReadingQuestionService.convertToEntity(testReadingQuestionService.findById(dto.getQuestionId())));
        entity.setAnswer(testReadingAnswerService.convertToEntity(testReadingAnswerService.findById(dto.getAnswerId())));
        entity.setComment(dto.getComment());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected SubmitTestReadingAnswerDTO convertToDTO(SubmitTestReadingAnswer entity) {
        SubmitTestReadingAnswerDTO dto = new SubmitTestReadingAnswerDTO();
        dto.setId(entity.getId());
        dto.setSubmitTestId(entity.getSubmitTest().getId());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setAnswerId(entity.getAnswer().getId());
        dto.setComment(entity.getComment());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
