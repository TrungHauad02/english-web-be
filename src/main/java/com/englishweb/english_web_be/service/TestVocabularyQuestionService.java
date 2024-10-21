package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestVocabularyQuestionDTO;
import com.englishweb.english_web_be.model.TestVocabularyQuestion;
import com.englishweb.english_web_be.repository.TestVocabularyQuestionRepository;

public class TestVocabularyQuestionService extends BaseService<TestVocabularyQuestion, TestVocabularyQuestionDTO, TestVocabularyQuestionRepository>{

    private final TestService testService;


    public TestVocabularyQuestionService(TestVocabularyQuestionRepository repository, TestService testService) {
        super(repository);
        this.testService = testService;
    }

    @Override
    protected TestVocabularyQuestion convertToEntity(TestVocabularyQuestionDTO dto) {
        TestVocabularyQuestion entity = new TestVocabularyQuestion();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setContent(dto.getContent());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));

        return null;
    }

    @Override
    protected TestVocabularyQuestionDTO convertToDTO(TestVocabularyQuestion entity) {
        return null;
    }
}
