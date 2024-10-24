package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestGrammarQuestionDTO;
import com.englishweb.english_web_be.dto.TestWritingDTO;
import com.englishweb.english_web_be.model.TestGrammarQuestion;
import com.englishweb.english_web_be.model.TestWriting;
import com.englishweb.english_web_be.repository.TestGrammarQuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestGrammarQuestionService extends BaseService<TestGrammarQuestion, TestGrammarQuestionDTO, TestGrammarQuestionRepository>{

    private final TestService testService;
    private final TestGrammarAnswerService testGrammarAnswerService;

    public TestGrammarQuestionService(TestGrammarQuestionRepository repository, @Lazy TestService testService,  @Lazy TestGrammarAnswerService testGrammarAnswerService) {
        super(repository);
        this.testService = testService;
        this.testGrammarAnswerService = testGrammarAnswerService;
    }



    public List<TestGrammarQuestionDTO> findAllByTestId(String test_id) {
        testService.isExist(test_id);
        List<TestGrammarQuestion> list = repository.findAllByTest_Id(test_id);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestGrammarQuestion convertToEntity(TestGrammarQuestionDTO dto) {
        TestGrammarQuestion entity = new TestGrammarQuestion();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setContent(dto.getContent());
        entity.setExplanation(dto.getExplanation());
        entity.setStatus(dto.getStatus());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));
        return entity;
    }

    @Override
    protected TestGrammarQuestionDTO convertToDTO(TestGrammarQuestion entity) {
        TestGrammarQuestionDTO dto = new TestGrammarQuestionDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setStatus(entity.getStatus());
        dto.setTestId(entity.getTest().getId());
        dto.setAnswers(testGrammarAnswerService.findAllByQuestionId(entity.getId()));
        return dto;
    }
}
