package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SubmitTestListeningAnswerDTO;
import com.englishweb.english_web_be.model.SubmitTestListeningAnswer;
import com.englishweb.english_web_be.model.TestListeningQuestion;
import com.englishweb.english_web_be.repository.SubmitTestListeningAnswerRepository;
import com.englishweb.english_web_be.service.SubmitTestListeningAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmitTestListeningAnswerServiceImpl extends BaseServiceImpl<SubmitTestListeningAnswer, SubmitTestListeningAnswerDTO, SubmitTestListeningAnswerRepository>
        implements SubmitTestListeningAnswerService {

    private final SubmitTestServiceImpl submitTestService;
    private final TestListeningQuestionServiceImpl testListeningQuestionService;
    private final TestListeningAnswerServiceImpl testListeningAnswerService;

    public SubmitTestListeningAnswerServiceImpl(SubmitTestListeningAnswerRepository repository,
                                                @Lazy SubmitTestServiceImpl submitTestService,
                                                @Lazy TestListeningQuestionServiceImpl testListeningQuestionService,
                                                @Lazy TestListeningAnswerServiceImpl testListeningAnswerService) {
        super(repository);
        this.submitTestService = submitTestService;
        this.testListeningQuestionService = testListeningQuestionService;
        this.testListeningAnswerService = testListeningAnswerService;
    }
    public List<SubmitTestListeningAnswerDTO> findAllBySubmitTestId(String submitTestId) {

        submitTestService.isExist(submitTestId);
        List<SubmitTestListeningAnswer> list = repository.findAllBySubmitTest_Id(submitTestId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected SubmitTestListeningAnswer convertToEntity(SubmitTestListeningAnswerDTO dto) {
        SubmitTestListeningAnswer entity = new SubmitTestListeningAnswer();
        entity.setId(dto.getId());
        entity.setSubmitTest(submitTestService.convertToEntity(submitTestService.findById(dto.getSubmitTestId())));
        entity.setQuestion(testListeningQuestionService.convertToEntity(testListeningQuestionService.findById(dto.getQuestionId())));
        entity.setAnswer(testListeningAnswerService.convertToEntity(testListeningAnswerService.findById(dto.getAnswerId())));
        entity.setComment(dto.getComment());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected SubmitTestListeningAnswerDTO convertToDTO(SubmitTestListeningAnswer entity) {
        SubmitTestListeningAnswerDTO dto = new SubmitTestListeningAnswerDTO();
        dto.setId(entity.getId());
        dto.setSubmitTestId(entity.getSubmitTest().getId());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setAnswerId(entity.getAnswer().getId());
        dto.setComment(entity.getComment());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
