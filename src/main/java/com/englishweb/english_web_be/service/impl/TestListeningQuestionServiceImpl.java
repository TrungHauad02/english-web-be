package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.model.TestListeningQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.TestListeningQuestionRepository;
import com.englishweb.english_web_be.service.TestListeningQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TestListeningQuestionServiceImpl extends BaseServiceImpl<TestListeningQuestion, TestListeningQuestionDTO, TestListeningQuestionRepository> implements TestListeningQuestionService {

    private final TestListeningServiceImpl testListeningService;
    private final TestListeningAnswerServiceImpl testListeningAnswerService;

    public TestListeningQuestionServiceImpl(TestListeningQuestionRepository repository, @Lazy TestListeningServiceImpl testListeningService, @Lazy TestListeningAnswerServiceImpl testListeningAnswerService) {
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
    public List<TestListeningQuestionDTO> findAllByTestListening_IdAndStatus(String testListeningId, StatusEnum status) {
        if(status == null){
            return findAllByTestListening_Id(testListeningId);
        }
        testListeningService.isExist(testListeningId);
        List<TestListeningQuestion> list = repository.findAllByTestListening_IdAndStatus(testListeningId,status);

        return list.stream()
                .map(entity -> {
                    TestListeningQuestionDTO dto = convertToDTO(entity);
                    dto.setAnswers(testListeningAnswerService.findAllByQuestionIdAndStatus(entity.getId(),status));
                    return dto;
                })
                .collect(Collectors.toList());
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
    @Override
    public void delete(String id) {

        List<TestListeningAnswerDTO> answers = testListeningAnswerService.findAllByQuestionId(id);
        if (answers != null) {
            for (TestListeningAnswerDTO answer : answers) {
                testListeningAnswerService.delete(answer.getId());
            }
        }
        super.delete(id);
    }
}