package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestListeningRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningQuestionResponseDTO;
import com.englishweb.english_web_be.dto.response.TestListeningResponseDTO;
import com.englishweb.english_web_be.mapper.TestListeningMapper;
import com.englishweb.english_web_be.model.TestListening;
import com.englishweb.english_web_be.repository.TestListeningRepository;
import com.englishweb.english_web_be.service.TestListeningService;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class TestListeningServiceImpl extends BaseServiceImpl<TestListening, TestListeningDTO, TestListeningRequestDTO, TestListeningResponseDTO, TestListeningMapper, TestListeningRepository> implements TestListeningService {

    private final TestServiceImpl testService;
    private final TestListeningQuestionServiceImpl testListeningQuestionService;
    TestListeningMapper testListeningMapper;

    public TestListeningServiceImpl(TestListeningRepository repository,
                                    @Lazy TestServiceImpl testService,
                                    @Lazy TestListeningQuestionServiceImpl testListeningQuestionService,
                                    @Lazy TestListeningMapper mapper) {
        super(repository, mapper);
        this.testService = testService;
        this.testListeningQuestionService = testListeningQuestionService;
    }

    public List<TestListeningResponseDTO> findAllByTestId(String testId) {
        testService.isExist(testId);
        List<TestListening> list = repository.findAllByTest_Id(testId);

        if (list.isEmpty()) {
            return null;
        }

        List<TestListeningDTO> dtoList = list.stream()
                .map(this::convertToDTO)
                .toList();

        return dtoList.stream()
                .map(mapper::mapToResponseDTO)
                .toList();
    }
    public List<TestListeningDTO> findAllDTOByTestId(String testId) {
        testService.isExist(testId);
        List<TestListening> list = repository.findAllByTest_Id(testId);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestListening convertToEntity(TestListeningDTO dto) {
        TestListening entity = new TestListening();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setTranscript(dto.getTranscript());
        entity.setTest(testService.convertToEntity(testService.findDTOById(dto.getTestId())));
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
        dto.setQuestions(testListeningQuestionService.findAllDTOByTestListening_Id(entity.getId()));
        return dto;
    }

    @Override
    public void delete(String id) {
        List<TestListeningQuestionResponseDTO> questions = testListeningQuestionService.findAllByTestListening_Id(id);
        if (questions != null) {
            for (TestListeningQuestionResponseDTO question : questions) {
                testListeningQuestionService.delete(question.getId());
            }
        }
        super.delete(id);
    }


}
