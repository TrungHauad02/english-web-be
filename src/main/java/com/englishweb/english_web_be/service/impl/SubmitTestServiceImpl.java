package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SubmitTestDTO;
import com.englishweb.english_web_be.model.SubmitTest;
import com.englishweb.english_web_be.repository.SubmitTestRepository;
import com.englishweb.english_web_be.service.SubmitTestService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SubmitTestServiceImpl extends BaseServiceImpl<SubmitTest, SubmitTestDTO, SubmitTestRepository>
        implements SubmitTestService {

    private final TestServiceImpl testService;
    private final UserServiceImpl userService;
    private final SubmitTestMixingAnswerServiceImpl submitTestMixingAnswerService;
    private final SubmitTestListeningAnswerServiceImpl submitTestListeningAnswerService;
    private final SubmitTestReadingAnswerServiceImpl submitTestReadingAnswerService;
    private final SubmitTestSpeakingServiceImpl submitTestSpeakingService;
    private final SubmitTestWritingServiceImpl submitTestWritingService;

    public SubmitTestServiceImpl(SubmitTestRepository repository,
                                 TestServiceImpl testService,
                                 UserServiceImpl userService,
                                 @Lazy SubmitTestMixingAnswerServiceImpl submitTestMixingAnswerService,
                                 @Lazy SubmitTestListeningAnswerServiceImpl submitTestListeningAnswerService,
                                 @Lazy SubmitTestReadingAnswerServiceImpl submitTestReadingAnswerService,
                                 @Lazy SubmitTestSpeakingServiceImpl submitTestSpeakingService,
                                 @Lazy SubmitTestWritingServiceImpl submitTestWritingService) {
        super(repository);
        this.testService = testService;
        this.userService = userService;
        this.submitTestMixingAnswerService = submitTestMixingAnswerService;
        this.submitTestListeningAnswerService = submitTestListeningAnswerService;
        this.submitTestReadingAnswerService = submitTestReadingAnswerService;
        this.submitTestSpeakingService = submitTestSpeakingService;
        this.submitTestWritingService = submitTestWritingService;
    }

    @Override
    protected SubmitTest convertToEntity(SubmitTestDTO dto) {
        SubmitTest entity = new SubmitTest();
        entity.setId(dto.getId());
        entity.setScore(dto.getScore());
        entity.setSubmitTime(dto.getSubmitTime());
        entity.setStatus(dto.getStatus());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));
        entity.setUser(userService.convertToEntity(userService.findById(dto.getUserId())));
        return entity;
    }

    @Override
    protected SubmitTestDTO convertToDTO(SubmitTest entity) {
        SubmitTestDTO dto = new SubmitTestDTO();
        dto.setId(entity.getId());
        dto.setScore(entity.getScore());
        dto.setSubmitTime(entity.getSubmitTime());
        dto.setStatus(entity.getStatus());
        dto.setTestId(entity.getTest().getId());
        dto.setUserId(entity.getUser().getId());


        dto.setSubmitTestMixingAnswers(submitTestMixingAnswerService.findAllBySubmitTestId(entity.getId()));
        dto.setSubmitTestListeningAnswers(submitTestListeningAnswerService.findAllBySubmitTestId(entity.getId()));
        dto.setSubmitTestReadingAnswers(submitTestReadingAnswerService.findAllBySubmitTestId(entity.getId()));
        dto.setSubmitTestSpeakings(submitTestSpeakingService.findAllBySubmitTestId(entity.getId()));
        dto.setSubmitTestWritings(submitTestWritingService.findAllBySubmitTestId(entity.getId()));

        return dto;
    }
}
