package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.*;
import com.englishweb.english_web_be.model.SubmitTest;
import com.englishweb.english_web_be.model.SubmitTestMixingAnswer;
import com.englishweb.english_web_be.model.SubmitTestReadingAnswer;
import com.englishweb.english_web_be.model.SubmitTestWriting;
import com.englishweb.english_web_be.modelenum.StatusEnum;
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
    public Page<SubmitTestDTO> findSubmitTestsBySpecification(String title, TestTypeEnum type, int page, int size, String startDate, String endDate, String userId, StatusEnum status) {

        Pageable pageable = PageRequest.of(page, size);



        Specification<SubmitTest> spec = Specification.where(SubmitTestSpecification.hasTestTitle(title).and(SubmitTestSpecification.hasUserId(userId)).and(SubmitTestSpecification.hasTestStatus(status)));

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
                    dto.setTestId(submitTest.getTest().getId());
                    dto.setUserId(submitTest.getUser().getId());
                    dto.setTestType(submitTest.getTest().getType());
                    return dto;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, submitTestPage.getPageable(), submitTestPage.getTotalElements());
    }

    public List<SubmitTestDTO> findAllByTestId(String testId) {
        testService.isExist(testId);
        List<SubmitTest> list = repository.findAllByTest_Id(testId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }


    public BigDecimal scoreLastSubmitTest(String testId,String userId) {

        List<SubmitTest> submitTests = submitTestRepository.findAllByTest_IdAndUserId(testId,userId);

        return submitTests.stream()
                .max(Comparator.comparing(SubmitTest::getSubmitTime))
                .map(SubmitTest::getScore)
                .orElse(BigDecimal.valueOf(-1));
    }


    @Override
    public void delete(String id) {

        List<SubmitTestSpeakingDTO> submitTestSpeakingDTOS = submitTestSpeakingService.findAllBySubmitTestId(id);
        if (submitTestSpeakingDTOS != null) {
            for (SubmitTestSpeakingDTO submitTestSpeakingDTO : submitTestSpeakingDTOS) {
                submitTestSpeakingService.delete(submitTestSpeakingDTO.getId());
            }
        }
        List<SubmitTestListeningAnswerDTO> submitTestListeningAnswerDTOS = submitTestListeningAnswerService.findAllBySubmitTestId(id);
        if (submitTestListeningAnswerDTOS != null) {
            for (SubmitTestListeningAnswerDTO submitTestListeningAnswerDTO : submitTestListeningAnswerDTOS) {
                submitTestListeningAnswerService.delete(submitTestListeningAnswerDTO.getId());
            }
        }
        List<SubmitTestReadingAnswerDTO> submitTestReadingAnswerDTOS = submitTestReadingAnswerService.findAllBySubmitTestId(id);
        if (submitTestReadingAnswerDTOS != null) {
            for (SubmitTestReadingAnswerDTO submitTestReadingAnswerDTO : submitTestReadingAnswerDTOS) {
                submitTestReadingAnswerService.delete(submitTestReadingAnswerDTO.getId());
            }
        }
        List<SubmitTestMixingAnswerDTO> submitTestMixingAnswerDTOS = submitTestMixingAnswerService.findAllBySubmitTestId(id);
        if (submitTestMixingAnswerDTOS != null) {
            for (SubmitTestMixingAnswerDTO submitTestMixingAnswerDTO : submitTestMixingAnswerDTOS) {
                submitTestMixingAnswerService.delete(submitTestMixingAnswerDTO.getId());
            }
        }
        List<SubmitTestWritingDTO> submitTestWritingDTOS = submitTestWritingService.findAllBySubmitTestId(id);
        if (submitTestWritingDTOS != null) {
            for (SubmitTestWritingDTO submitTestWritingDTO : submitTestWritingDTOS) {
                submitTestWritingService.delete(submitTestWritingDTO.getId());
            }
        }

        super.delete(id);
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
