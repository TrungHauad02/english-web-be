package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SubmitTestDTO;
import com.englishweb.english_web_be.model.SubmitTest;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.SubmitTestRepository;
import com.englishweb.english_web_be.service.SubmitTestService;
import com.englishweb.english_web_be.util.SubmitTestSpecification;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private final SubmitTestRepository submitTestRepository;

    public SubmitTestServiceImpl(SubmitTestRepository repository,
                                 TestServiceImpl testService,
                                 UserServiceImpl userService,
                                 @Lazy SubmitTestMixingAnswerServiceImpl submitTestMixingAnswerService,
                                 @Lazy SubmitTestListeningAnswerServiceImpl submitTestListeningAnswerService,
                                 @Lazy SubmitTestReadingAnswerServiceImpl submitTestReadingAnswerService,
                                 @Lazy SubmitTestSpeakingServiceImpl submitTestSpeakingService,
                                 @Lazy SubmitTestWritingServiceImpl submitTestWritingService, SubmitTestRepository submitTestRepository) {
        super(repository);
        this.testService = testService;
        this.userService = userService;
        this.submitTestMixingAnswerService = submitTestMixingAnswerService;
        this.submitTestListeningAnswerService = submitTestListeningAnswerService;
        this.submitTestReadingAnswerService = submitTestReadingAnswerService;
        this.submitTestSpeakingService = submitTestSpeakingService;
        this.submitTestWritingService = submitTestWritingService;
        this.submitTestRepository = submitTestRepository;
    }

    @Override
    public Page<SubmitTestDTO> findSubmitTestsBySpecification(String title, TestTypeEnum type, int page, int size, String startDate, String endDate) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<SubmitTest> spec = Specification.where(SubmitTestSpecification.hasTestTitle(title));
        if (type != null) {
            spec = spec.and(SubmitTestSpecification.hasTestType(type));
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            LocalDateTime start = null;
            LocalDateTime end = null;

            if (startDate != null && !startDate.isEmpty()) {
                start = LocalDateTime.parse(startDate, formatter);
            }
            if (endDate != null && !endDate.isEmpty()) {
                end = LocalDateTime.parse(endDate, formatter);
            }

            spec = spec.and(SubmitTestSpecification.hasSubmitTimeRange(start, end));

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use ISO-8601 format.");
        }


        Page<SubmitTest> submitTestPage = submitTestRepository.findAll( spec,pageable);
        List<SubmitTestDTO> dtoList = submitTestPage.getContent().stream()
                .map(submitTest -> {
                    SubmitTestDTO dto = new SubmitTestDTO();
                    dto.setId(submitTest.getId());
                    dto.setTestTitle(submitTest.getTest().getTitle());
                    dto.setSubmitTime(submitTest.getSubmitTime());
                    dto.setScore(submitTest.getScore());
                    dto.setStatus(submitTest.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, submitTestPage.getPageable(), submitTestPage.getTotalElements());
    }

    public BigDecimal scoreLastSubmitTest(String testId) {

        List<SubmitTest> submitTests = submitTestRepository.findAllByTest_Id(testId);

        return submitTests.stream()
                .max(Comparator.comparing(SubmitTest::getSubmitTime))
                .map(SubmitTest::getScore)
                .orElse(BigDecimal.valueOf(0));
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
        dto.setTestTitle(entity.getTest().getTitle());

        dto.setSubmitTestMixingAnswers(submitTestMixingAnswerService.findAllBySubmitTestId(entity.getId()));
        dto.setSubmitTestListeningAnswers(submitTestListeningAnswerService.findAllBySubmitTestId(entity.getId()));
        dto.setSubmitTestReadingAnswers(submitTestReadingAnswerService.findAllBySubmitTestId(entity.getId()));
        dto.setSubmitTestSpeakings(submitTestSpeakingService.findAllBySubmitTestId(entity.getId()));
        dto.setSubmitTestWritings(submitTestWritingService.findAllBySubmitTestId(entity.getId()));

        return dto;
    }
}
