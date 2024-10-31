package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.TestReadingDTO;
import com.englishweb.english_web_be.model.TestListening;
import com.englishweb.english_web_be.model.TestReading;
import com.englishweb.english_web_be.repository.TestListeningRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestListeningService extends BaseService<TestListening, TestListeningDTO, TestListeningRepository> {

    private final TestService testService;
    private final TestListeningQuestionService testListeningQuestionService;



    public List<TestListeningDTO> findAllByTestId(String test_id) {
        testService.isExist(test_id);
        List<TestListening> list = repository.findAllByTest_Id(test_id);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public TestListeningService(TestListeningRepository repository, @Lazy TestService testService, @Lazy TestListeningQuestionService testListeningQuestionService) {
        super(repository);
        this.testService = testService;
        this.testListeningQuestionService = testListeningQuestionService;
    }

    @Override
    protected TestListening convertToEntity(TestListeningDTO dto) {
        TestListening entity = new TestListening();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setStatusEnum(dto.getStatus());
        entity.setTranscript(dto.getTranscript());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));
        return entity;
    }

    @Override
    protected TestListeningDTO convertToDTO(TestListening entity) {
        TestListeningDTO dto = new TestListeningDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        dto.setTranscript(entity.getTranscript());
        dto.setTestId(entity.getTest().getId());
        dto.setQuestions(testListeningQuestionService.findAllByTestListening_Id(entity.getId()));
        return dto;
    }
    @Override
    public void delete(String id) {

        List<TestListeningQuestionDTO> questions = testListeningQuestionService.findAllByTestListening_Id(id);
        if (questions != null) {
            for (TestListeningQuestionDTO question : questions) {
                testListeningQuestionService.delete(question.getId());
            }
        }
        super.delete(id);
    }
}
