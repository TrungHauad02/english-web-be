package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.model.ListeningQuestion;
import com.englishweb.english_web_be.model.TestListeningQuestion;
import com.englishweb.english_web_be.model.TestReadingQuestion;
import com.englishweb.english_web_be.repository.TestListeningQuestionRepository;
import com.englishweb.english_web_be.repository.TestListeningRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TestListeningQuestionService extends BaseService<TestListeningQuestion, TestListeningQuestionDTO, TestListeningQuestionRepository> {

    private final TestListeningService testListeningService;
    private final TestListeningAnswerService testListeningAnswerService;



    public TestListeningQuestionService(TestListeningQuestionRepository repository, @Lazy TestListeningService testListeningService,  @Lazy TestListeningAnswerService testListeningAnswerService) {
        super(repository);
        this.testListeningService = testListeningService;
        this.testListeningAnswerService = testListeningAnswerService;
    }

    public List<TestListeningQuestionDTO> findAllByTestListening_Id(String testListeningId) {
        testListeningService.isExist(testListeningId);
        List<TestListeningQuestion> list = repository.findAllByTestListening_Id(testListeningId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestListeningQuestion convertToEntity(TestListeningQuestionDTO dto) {
        TestListeningQuestion entity = new TestListeningQuestion();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setTestListening(testListeningService.convertToEntity(testListeningService.findById(dto.getTestListeningId())));
        return entity;
    }

    @Override
    protected TestListeningQuestionDTO convertToDTO(TestListeningQuestion entity) {
        TestListeningQuestionDTO dto = new TestListeningQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        dto.setAnswers(testListeningAnswerService.findAllByQuestionId(entity.getId()));
        dto.setTestListeningId(entity.getTestListening().getId());
        return dto;
    }
}
