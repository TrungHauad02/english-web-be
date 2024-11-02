package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestListeningQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningAnswerResponseDTO;
import com.englishweb.english_web_be.dto.response.TestListeningQuestionResponseDTO;
import com.englishweb.english_web_be.mapper.TestListeningQuestionMapper;
import com.englishweb.english_web_be.model.TestListeningQuestion;
import com.englishweb.english_web_be.repository.TestListeningQuestionRepository;
import com.englishweb.english_web_be.service.TestListeningQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestListeningQuestionServiceImpl extends BaseServiceImpl<TestListeningQuestion, TestListeningQuestionDTO, TestListeningQuestionRequestDTO, TestListeningQuestionResponseDTO, TestListeningQuestionMapper, TestListeningQuestionRepository>
        implements TestListeningQuestionService {

    private final TestListeningServiceImpl testListeningService;
    private final TestListeningAnswerServiceImpl testListeningAnswerService;
    TestListeningQuestionMapper mapper;

    public TestListeningQuestionServiceImpl(TestListeningQuestionRepository repository, @Lazy TestListeningServiceImpl testListeningService, @Lazy TestListeningAnswerServiceImpl testListeningAnswerService) {
        super(repository);
        this.testListeningService = testListeningService;
        this.testListeningAnswerService = testListeningAnswerService;
    }
    @Override
    public List<TestListeningQuestionResponseDTO> findAllByTestListening_Id(String testListeningId) {
        testListeningService.isExist(testListeningId);
        List<TestListeningQuestion> list = repository.findAllByTestListening_Id(testListeningId);

        List<TestListeningQuestionDTO> dtoList = list.stream()
                .map(this::convertToDTO)
                .toList();

        return dtoList.stream()
                .map(mapper::mapToResponseDTO)
                .toList();
    }


    public List<TestListeningQuestionDTO> findAllDTOByTestListening_Id(String testListeningId) {
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
        entity.setTestListening(testListeningService.convertToEntity(testListeningService.findDTOById(dto.getTestListeningId())));
        return entity;
    }

    @Override
    protected TestListeningQuestionDTO convertToDTO(TestListeningQuestion entity) {
        TestListeningQuestionDTO dto = new TestListeningQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        dto.setTestListeningId(entity.getTestListening().getId());
        dto.setAnswers(testListeningAnswerService.findAllDTOByQuestionId(entity.getId()));
        return dto;
    }

    @Override
    public void delete(String id) {
        List<TestListeningAnswerResponseDTO> answers = testListeningAnswerService.findAllByQuestionId(id);
        if (answers != null) {
            for (TestListeningAnswerResponseDTO answer : answers) {
                testListeningAnswerService.delete(answer.getId());
            }
        }
        super.delete(id);
    }


}
