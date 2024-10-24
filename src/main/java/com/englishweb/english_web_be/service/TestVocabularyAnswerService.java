package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestVocabularyAnswerDTO;
import com.englishweb.english_web_be.model.TestVocabularyAnswer;
import com.englishweb.english_web_be.model.TestVocabularyQuestion;
import com.englishweb.english_web_be.repository.TestVocabularyAnswerRepository;
import com.englishweb.english_web_be.repository.TestVocabularyQuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestVocabularyAnswerService extends BaseService<TestVocabularyAnswer, TestVocabularyAnswerDTO, TestVocabularyAnswerRepository> {
    private final TestVocabularyQuestionService testVocabularyQuestionService;


    public TestVocabularyAnswerService(TestVocabularyAnswerRepository repository,   @Lazy TestVocabularyQuestionService testVocabularyQuestionService) {
        super(repository);
        this.testVocabularyQuestionService = testVocabularyQuestionService;
    }


    public List<TestVocabularyAnswerDTO> findAllByQuestionId(String questionId) {
        testVocabularyQuestionService.isExist(questionId);
        List<TestVocabularyAnswer> list = repository.findAllByTestVocabularyQuestion_Id(questionId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestVocabularyAnswer convertToEntity(TestVocabularyAnswerDTO dto) {

        TestVocabularyAnswer entity = new TestVocabularyAnswer();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setIsCorrect(dto.getIsCorrect());
        entity.setTestVocabularyQuestion(testVocabularyQuestionService.convertToEntity(testVocabularyQuestionService.findById(dto.getTestIdQuestionVocabulary())));
        return entity;
    }

    @Override
    protected TestVocabularyAnswerDTO convertToDTO(TestVocabularyAnswer entity) {
        TestVocabularyAnswerDTO dto = new TestVocabularyAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setIsCorrect(entity.getIsCorrect());
        dto.setTestIdQuestionVocabulary(entity.getTestVocabularyQuestion().getId());
        return dto;
    }
}
