package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.dto.TestMixingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestMixingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.request.TestMixingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestMixingAnswerResponseDTO;
import com.englishweb.english_web_be.dto.response.TestMixingQuestionResponseDTO;
import com.englishweb.english_web_be.mapper.TestMixingQuestionMapper;
import com.englishweb.english_web_be.model.TestMixingQuestion;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import com.englishweb.english_web_be.repository.TestMixingQuestionRepository;
import com.englishweb.english_web_be.service.TestMixingQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestMixingQuestionServiceImpl extends BaseServiceImpl<TestMixingQuestion,
        TestMixingQuestionDTO, TestMixingQuestionRequestDTO, TestMixingQuestionResponseDTO, TestMixingQuestionMapper, TestMixingQuestionRepository>
        implements TestMixingQuestionService {

    private final TestServiceImpl testService;
    private final TestMixingAnswerServiceImpl testMixingAnswerService;


    public TestMixingQuestionServiceImpl(TestMixingQuestionRepository repository,
                                         @Lazy TestServiceImpl testService,
                                         @Lazy TestMixingAnswerServiceImpl testMixingAnswerService,
                                         @Lazy TestMixingQuestionMapper mapper) {
        super(repository, mapper);
        this.testService = testService;
        this.testMixingAnswerService = testMixingAnswerService;
    }

    public List<TestMixingQuestionResponseDTO> findAllByTestId(String testId) {
        testService.isExist(testId);
        List<TestMixingQuestion> list = repository.findAllByTest_Id(testId);

        if (list.isEmpty()) {
            return null;
        }

        List<TestMixingQuestionDTO> dtoList = list.stream()
                .map(this::convertToDTO)
                .toList();

        return dtoList.stream()
                .map(mapper::mapToResponseDTO)
                .toList();
    }


    public List<TestMixingQuestionDTO> findAllDTOByTestId(String testId) {
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
    public List<TestMixingQuestionDTO> findAllDTOByTest_IdAndType(String testId, TestMixingTypeEnum typeEnum) {
        testService.isExist(testId);
        List<TestMixingQuestion> list = repository.findAllByTest_IdAndType(testId, typeEnum);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TestMixingQuestionResponseDTO> findAllByTest_IdAndType(String testId, TestMixingTypeEnum typeEnum) {
        testService.isExist(testId);
        List<TestMixingQuestion> list = repository.findAllByTest_IdAndType(testId, typeEnum);

        if (list.isEmpty()) {
            return null;
        }
        List<TestMixingQuestionDTO> dtoList = list.stream()
                .map(this::convertToDTO)
                .toList();

        return dtoList.stream()
                .map(mapper::mapToResponseDTO)
                .toList();
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
        entity.setTest(testService.convertToEntity(testService.findDTOById(dto.getTestId())));
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
        dto.setAnswers(testMixingAnswerService.findAllDTOByQuestionId(entity.getId()));
        return dto;
    }

    @Override
    public void delete(String id) {
        List<TestMixingAnswerResponseDTO> answers = testMixingAnswerService.findAllByQuestionId(id);
        if (answers != null) {
            for (TestMixingAnswerResponseDTO answer : answers) {
                testMixingAnswerService.delete(answer.getId());
            }
        }
        super.delete(id);
    }

    @Override
    public TestMixingQuestionResponseDTO create(TestMixingQuestionRequestDTO dto) {

        TestMixingQuestionResponseDTO savedQuestion = super.create(dto);

        List<TestMixingAnswerRequestDTO> answers = dto.getAnswers();
        if (answers != null) {
            for (TestMixingAnswerRequestDTO answer : answers) {
                answer.setTestQuestionMixingId(savedQuestion.getId());
                testMixingAnswerService.create(answer);
            }
        }

        return savedQuestion;
    }

    @Override
    public TestMixingQuestionResponseDTO update(TestMixingQuestionRequestDTO dto, String id) {


        TestMixingQuestionResponseDTO savedQuestion = super.update(dto, id);
        List<TestMixingAnswerRequestDTO> answers = dto.getAnswers();
        for (TestMixingAnswerRequestDTO answer : answers) {
            try {
                testMixingAnswerService.update(answer, answer.getId());
            } catch (Exception e) {
                testMixingAnswerService.create(answer);
            }
        }

        return savedQuestion;
    }
}
