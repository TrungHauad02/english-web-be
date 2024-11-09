package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningAnswerResponseDTO;
import com.englishweb.english_web_be.mapper.TestListeningAnswerMapper;
import com.englishweb.english_web_be.model.TestListeningAnswer;
import com.englishweb.english_web_be.repository.TestListeningAnswerRepository;
import com.englishweb.english_web_be.service.TestListeningAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestListeningAnswerServiceImpl extends BaseServiceImpl<TestListeningAnswer,
        TestListeningAnswerDTO, TestListeningAnswerRequestDTO, TestListeningAnswerResponseDTO, TestListeningAnswerMapper, TestListeningAnswerRepository>
        implements TestListeningAnswerService {

    private final TestListeningQuestionServiceImpl testListeningQuestionService;

    public TestListeningAnswerServiceImpl(TestListeningAnswerRepository repository, @Lazy TestListeningQuestionServiceImpl testListeningQuestionService, @Lazy TestListeningAnswerMapper mapper) {
        super(repository, mapper);
        this.testListeningQuestionService = testListeningQuestionService;
    }


    public List<TestListeningAnswerResponseDTO> findAllByQuestionId(String questionId) {
        testListeningQuestionService.isExist(questionId);
        List<TestListeningAnswer> list = repository.findAllByTestListeningQuestion_Id(questionId);

        List<TestListeningAnswerDTO> dtoList = list.stream()
                .map(this::convertToDTO)
                .toList();

        return dtoList.stream()
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    public List<TestListeningAnswerDTO> findAllDTOByQuestionId(String questionId) {
        testListeningQuestionService.isExist(questionId);
        List<TestListeningAnswer> list = repository.findAllByTestListeningQuestion_Id(questionId);


        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestListeningAnswer convertToEntity(TestListeningAnswerDTO dto) {
        TestListeningAnswer entity = new TestListeningAnswer();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setIsCorrect(dto.getIsCorrect());
        entity.setStatus(dto.getStatus());
        entity.setTestListeningQuestion(testListeningQuestionService.convertToEntity(testListeningQuestionService.findDTOById(dto.getTestQuestionListeningId())));
        return entity;
    }

    @Override
    protected TestListeningAnswerDTO convertToDTO(TestListeningAnswer entity) {
        TestListeningAnswerDTO dto = new TestListeningAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setIsCorrect(entity.getIsCorrect());
        dto.setStatus(entity.getStatus());
        dto.setTestQuestionListeningId(entity.getTestListeningQuestion().getId());
        return dto;
    }
}
