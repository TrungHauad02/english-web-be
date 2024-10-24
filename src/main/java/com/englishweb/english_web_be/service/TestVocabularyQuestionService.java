package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestVocabularyAnswerDTO;
import com.englishweb.english_web_be.dto.TestVocabularyQuestionDTO;
import com.englishweb.english_web_be.dto.TestWritingDTO;
import com.englishweb.english_web_be.model.TestVocabularyAnswer;
import com.englishweb.english_web_be.model.TestVocabularyQuestion;
import com.englishweb.english_web_be.model.TestWriting;
import com.englishweb.english_web_be.repository.TestVocabularyQuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TestVocabularyQuestionService extends BaseService<TestVocabularyQuestion, TestVocabularyQuestionDTO, TestVocabularyQuestionRepository>{

    private final TestService testService;
    private final TestVocabularyAnswerService testVocabularyAnswerService;


    public TestVocabularyQuestionService(TestVocabularyQuestionRepository repository, @Lazy TestService testService,  @Lazy TestVocabularyAnswerService testVocabularyAnswerService) {
        super(repository);
        this.testService = testService;
        this.testVocabularyAnswerService = testVocabularyAnswerService;
    }

    public List<TestVocabularyQuestionDTO> findAllByTest_Id(String test_id) {

        testService.isExist(test_id);
        List<TestVocabularyQuestion> list = repository.findAllByTest_Id(test_id);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }
    public List<TestVocabularyQuestionDTO> findAllByTestId(String test_id) {
        testService.isExist(test_id);
        List<TestVocabularyQuestion> list = repository.findAllByTest_Id(test_id);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }




    @Override
    protected TestVocabularyQuestion convertToEntity(TestVocabularyQuestionDTO dto) {
        TestVocabularyQuestion entity = new TestVocabularyQuestion();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setContent(dto.getContent());
        entity.setExplanation(dto.getExplanation());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));
        return entity;
    }

    @Override
    protected TestVocabularyQuestionDTO convertToDTO(TestVocabularyQuestion entity) {
        TestVocabularyQuestionDTO dto = new TestVocabularyQuestionDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setStatus(entity.getStatus());
        dto.setTestId(entity.getTest().getId());
        dto.setAnswers(testVocabularyAnswerService.findAllByQuestionId(entity.getId()));
        return dto;
    }
}
