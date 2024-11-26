package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.dto.TestMixingQuestionDTO;
import com.englishweb.english_web_be.model.TestMixingQuestion;
import com.englishweb.english_web_be.model.TestReading;
import com.englishweb.english_web_be.model.TestReadingQuestion;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import com.englishweb.english_web_be.repository.TestMixingQuestionRepository;
import com.englishweb.english_web_be.service.TestMixingQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TestMixingQuestionServiceImpl extends BaseServiceImpl<TestMixingQuestion, TestMixingQuestionDTO, TestMixingQuestionRepository> implements TestMixingQuestionService {

    private final TestServiceImpl testService;
    private final TestMixingAnswerServiceImpl testMixingAnswerService;

    public TestMixingQuestionServiceImpl(TestMixingQuestionRepository repository,
                                         @Lazy TestServiceImpl testService,
                                         @Lazy TestMixingAnswerServiceImpl testMixingAnswerService) {
        super(repository);
        this.testService = testService;
        this.testMixingAnswerService = testMixingAnswerService;
    }

    public List<TestMixingQuestionDTO> findAllByTestId(String testId) {
        testService.isExist(testId);
        List<TestMixingQuestion> list = repository.findAllByTest_Id(testId);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TestMixingQuestionDTO> findAllByTest_IdAndType(String testId, TestMixingTypeEnum typeEnum) {
        testService.isExist(testId);
        List<TestMixingQuestion> list = repository.findAllByTest_IdAndType(testId,typeEnum);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }
    public int serialMaxMixingQuestionsByTestId(String testId) {

        testService.isExist(testId);

        List<TestMixingQuestion> list = repository.findAllByTest_Id(testId);

        if (list.isEmpty()) {
            return 0;
        }

        TestMixingQuestion lastQuestion = list.get(list.size() - 1);

        return lastQuestion.getSerial();
    }



    @Override
    protected TestMixingQuestion convertToEntity(TestMixingQuestionDTO dto) {
        TestMixingQuestion entity = new TestMixingQuestion();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setContent(dto.getContent());
        entity.setExplanation(dto.getExplanation());
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));
        return entity;
    }

    @Override
    protected TestMixingQuestionDTO convertToDTO(TestMixingQuestion entity) {
        TestMixingQuestionDTO dto = new TestMixingQuestionDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setStatus(entity.getStatus());
        dto.setType(entity.getType());
        dto.setTestId(entity.getTest().getId());
        dto.setAnswers(testMixingAnswerService.findAllByQuestionId(entity.getId()));
        return dto;
    }
    @Override
    public void delete(String id) {

        List<TestMixingAnswerDTO> answers = testMixingAnswerService.findAllByQuestionId(id);
        if (answers != null) {
            for (TestMixingAnswerDTO answer : answers) {
                testMixingAnswerService.delete(answer.getId());
            }
        }
        super.delete(id);
    }
}