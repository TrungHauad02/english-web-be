package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SubmitTestMixingAnswerDTO;
import com.englishweb.english_web_be.model.SubmitTest;
import com.englishweb.english_web_be.model.SubmitTestListeningAnswer;
import com.englishweb.english_web_be.model.SubmitTestMixingAnswer;
import com.englishweb.english_web_be.repository.SubmitTestMixingAnswerRepository;
import com.englishweb.english_web_be.service.SubmitTestMixingAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmitTestMixingAnswerServiceImpl extends BaseServiceImpl<SubmitTestMixingAnswer, SubmitTestMixingAnswerDTO, SubmitTestMixingAnswerRepository>
        implements SubmitTestMixingAnswerService {

    private final TestMixingQuestionServiceImpl testMixingQuestionService;
    private final TestMixingAnswerServiceImpl testMixingAnswerService;
    private final SubmitTestServiceImpl submitTestService;

    public SubmitTestMixingAnswerServiceImpl(SubmitTestMixingAnswerRepository repository,
                                             @Lazy TestMixingQuestionServiceImpl testMixingQuestionService,
                                             @Lazy TestMixingAnswerServiceImpl testMixingAnswerService, SubmitTestServiceImpl submitTestService) {
        super(repository);
        this.testMixingQuestionService = testMixingQuestionService;
        this.testMixingAnswerService = testMixingAnswerService;
        this.submitTestService = submitTestService;
    }
    public List<SubmitTestMixingAnswerDTO> findAllBySubmitTestId(String submitTestId) {
        submitTestService.isExist(submitTestId);
        List<SubmitTestMixingAnswer> list = repository.findAllBySubmitTest_Id(submitTestId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected SubmitTestMixingAnswer convertToEntity(SubmitTestMixingAnswerDTO dto) {
        SubmitTestMixingAnswer entity = new SubmitTestMixingAnswer();
        entity.setId(dto.getId());
        entity.setSubmitTest(submitTestService.convertToEntity(submitTestService.findById(dto.getSubmitTestId())));
        entity.setQuestion(testMixingQuestionService.convertToEntity(testMixingQuestionService.findById(dto.getQuestionId())));
        entity.setAnswer(testMixingAnswerService.convertToEntity(testMixingAnswerService.findById(dto.getAnswerId())));
        entity.setComment(dto.getComment());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected SubmitTestMixingAnswerDTO convertToDTO(SubmitTestMixingAnswer entity) {
        SubmitTestMixingAnswerDTO dto = new SubmitTestMixingAnswerDTO();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setStatus(entity.getStatus());
        dto.setSubmitTestId(entity.getSubmitTest().getId());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setAnswerId(entity.getAnswer().getId());
        return dto;
    }
}
