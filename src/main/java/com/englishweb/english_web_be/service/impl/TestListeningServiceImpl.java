package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.model.TestListening;
import com.englishweb.english_web_be.repository.TestListeningRepository;
import com.englishweb.english_web_be.service.TestListeningService;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class TestListeningServiceImpl extends BaseServiceImpl<TestListening, TestListeningDTO, TestListeningRepository> implements TestListeningService {

    private final TestServiceImpl testService;
    @Getter
    private final TestListeningQuestionServiceImpl testListeningQuestionService;

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

    public TestListeningServiceImpl(TestListeningRepository repository, @Lazy TestServiceImpl testService, @Lazy TestListeningQuestionServiceImpl testListeningQuestionService) {
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
        entity.setStatus(dto.getStatus());
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

    public void deleteQuestionTest(String questionId) {

        testListeningQuestionService.delete(questionId);
    }

    public void updateQuestionTest(List<TestListeningQuestionDTO> questions) {

        if (questions != null) {
            for (TestListeningQuestionDTO question : questions) {
                testListeningQuestionService.update(question,question.getId());
            }
        }
    }

}
