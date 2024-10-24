package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestReadingAnswerDTO;
import com.englishweb.english_web_be.dto.TestReadingDTO;
import com.englishweb.english_web_be.dto.TestVocabularyAnswerDTO;
import com.englishweb.english_web_be.model.TestReadingAnswer;
import com.englishweb.english_web_be.model.TestReadingQuestion;
import com.englishweb.english_web_be.model.TestVocabularyAnswer;
import com.englishweb.english_web_be.repository.TestReadingAnswerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestReadingAnswerService extends BaseService<TestReadingAnswer, TestReadingAnswerDTO, TestReadingAnswerRepository> {


    private final TestReadingQuestionService testReadingQuestionService;

    public TestReadingAnswerService(TestReadingAnswerRepository repository,  @Lazy TestReadingQuestionService testReadingQuestionService) {
        super(repository);
        this.testReadingQuestionService = testReadingQuestionService;
    }


    public List<TestReadingAnswerDTO> findAllByQuestionId(String questionId) {
        testReadingQuestionService.isExist(questionId);
        List<TestReadingAnswer> list = repository.findAllByTestReadingQuestion_Id(questionId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestReadingAnswer convertToEntity(TestReadingAnswerDTO dto) {
        TestReadingAnswer entity = new TestReadingAnswer();
        entity.setId(dto.getId());
        entity.setIsCorrect(dto.getIsCorrect());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setTestReadingQuestion(testReadingQuestionService.convertToEntity(testReadingQuestionService.findById(dto.getTestQuestionReadingId())));

        return entity;
    }
    @Override
    protected TestReadingAnswerDTO convertToDTO(TestReadingAnswer entity) {
        TestReadingAnswerDTO dto = new TestReadingAnswerDTO();
        dto.setId(entity.getId());
        dto.setIsCorrect(entity.getIsCorrect());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setTestQuestionReadingId(entity.getTestReadingQuestion().getId());

        return dto;
    }
}
