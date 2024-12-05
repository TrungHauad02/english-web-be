package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestSpeakingDTO;
import com.englishweb.english_web_be.dto.TestSpeakingQuestionDTO;
import com.englishweb.english_web_be.model.TestSpeaking;
import com.englishweb.english_web_be.model.TestSpeakingQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.TestSpeakingRepository;
import com.englishweb.english_web_be.service.TestSpeakingService;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class TestSpeakingServiceImpl extends BaseServiceImpl<TestSpeaking, TestSpeakingDTO, TestSpeakingRepository> implements TestSpeakingService {

    private final TestServiceImpl testService;
    private final TestSpeakingQuestionServiceImpl testSpeakingQuestionService;

    public TestSpeakingServiceImpl(TestSpeakingRepository repository, @Lazy TestServiceImpl testService, @Lazy TestSpeakingQuestionServiceImpl testSpeakingQuestionService) {
        super(repository);
        this.testService = testService;
        this.testSpeakingQuestionService = testSpeakingQuestionService;
    }

    public List<TestSpeakingDTO> findAllByTest_Id(String testId) {
        testService.isExist(testId);
        List<TestSpeaking> list = repository.findAllByTest_Id(testId);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }
    public List<TestSpeakingDTO> findAllTestSpeakingByTestIdAndStatus(String testId, StatusEnum status) {
        if (status == null) {
            return findAllByTest_Id(testId);
        }

        testService.isExist(testId);

        List<TestSpeaking> list = repository.findAllByTest_IdAndStatus(testId, status);

        return list.stream()
                .map(entity -> {
                    TestSpeakingDTO dto = convertToDTO(entity);
                    dto.setQuestions(testSpeakingQuestionService.findAllByTestSpeaking_IdAndStatus(entity.getId(), status));
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public int totalActiveSpeakingQuestionsByTestId(String testId) {
        testService.isExist(testId);

        List<TestSpeaking> list = repository.findAllByTest_IdAndStatus(testId,StatusEnum.ACTIVE);

        if (list.isEmpty()) {
            return 0;
        }

        return list.stream()
                .mapToInt(speaking -> {
                    List<TestSpeakingQuestion> questions = speaking.getQuestions();
                    if (questions == null) {
                        return 0;
                    }
                    return (int) questions.stream()
                            .filter(question -> question.getStatus() == StatusEnum.ACTIVE)
                            .count();
                })
                .sum();
    }





    @Override
    protected TestSpeaking convertToEntity(TestSpeakingDTO dto) {
        TestSpeaking entity = new TestSpeaking();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));
        return entity;
    }

    @Override
    protected TestSpeakingDTO convertToDTO(TestSpeaking entity) {
        TestSpeakingDTO dto = new TestSpeakingDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        dto.setTestId(entity.getTest().getId());
        dto.setSerial(entity.getSerial());
        dto.setQuestions(testSpeakingQuestionService.findAllByTestSpeaking_Id(entity.getId()));
        return dto;
    }

    @Override
    public void delete(String id) {

        List<TestSpeakingQuestionDTO> questions = testSpeakingQuestionService.findAllByTestSpeaking_Id(id);
        if (questions != null) {
            for (TestSpeakingQuestionDTO question : questions) {
                testSpeakingQuestionService.delete(question.getId());
            }
        }
        super.delete(id);
    }


}