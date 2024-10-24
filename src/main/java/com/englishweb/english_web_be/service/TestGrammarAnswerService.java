package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestGrammarAnswerDTO;
import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.model.TestGrammarAnswer;
import com.englishweb.english_web_be.model.TestListeningAnswer;
import com.englishweb.english_web_be.repository.TestGrammarAnswerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestGrammarAnswerService extends BaseService<TestGrammarAnswer, TestGrammarAnswerDTO, TestGrammarAnswerRepository> {


    private final TestGrammarQuestionService testGrammarQuestionService;

    public TestGrammarAnswerService(TestGrammarAnswerRepository repository,  @Lazy TestGrammarQuestionService testGrammarQuestionService) {
        super(repository);
        this.testGrammarQuestionService = testGrammarQuestionService;
    }


    public List<TestGrammarAnswerDTO> findAllByQuestionId(String questionId) {
        testGrammarQuestionService.isExist(questionId);
        List<TestGrammarAnswer> list = repository.findAllByTestGrammarQuestion_Id(questionId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestGrammarAnswer convertToEntity(TestGrammarAnswerDTO dto) {
        TestGrammarAnswer entity = new TestGrammarAnswer();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setIsCorrect(dto.getIsCorrect());
        entity.setTestGrammarQuestion(testGrammarQuestionService.convertToEntity(testGrammarQuestionService.findById(dto.getTestQuestionGrammarId())));
        return entity;
    }

    @Override
    protected TestGrammarAnswerDTO convertToDTO(TestGrammarAnswer entity) {
        TestGrammarAnswerDTO dto = new TestGrammarAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setIsCorrect(entity.getIsCorrect());
        dto.setTestQuestionGrammarId(entity.getTestGrammarQuestion().getId());
        return dto;
    }
}
