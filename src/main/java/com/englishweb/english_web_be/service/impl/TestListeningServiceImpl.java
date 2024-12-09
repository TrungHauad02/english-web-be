package com.englishweb.english_web_be.service.impl;


import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;

import com.englishweb.english_web_be.model.TestListening;
import com.englishweb.english_web_be.model.TestListeningQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.TestListeningRepository;
import com.englishweb.english_web_be.service.TestListeningService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
@Slf4j
public class TestListeningServiceImpl extends BaseServiceImpl<TestListening, TestListeningDTO, TestListeningRepository> implements TestListeningService {

    private final TestServiceImpl testService;
    @Getter
    private final TestListeningQuestionServiceImpl testListeningQuestionService;
    private final FirebaseStorageServiceImpl firebaseStorageService;


    public TestListeningServiceImpl(TestListeningRepository repository, @Lazy TestServiceImpl testService, @Lazy TestListeningQuestionServiceImpl testListeningQuestionService, FirebaseStorageServiceImpl firebaseStorageService) {
        super(repository);
        this.testService = testService;
        this.testListeningQuestionService = testListeningQuestionService;
        this.firebaseStorageService = firebaseStorageService;
    }

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

    public List<TestListeningDTO> findAllByTestIdAndStatus(String test_id, StatusEnum status) {
        if (status == null) {
            return findAllByTestId(test_id);
        }

        testService.isExist(test_id);

        List<TestListening> list = repository.findAllByTest_IdAndStatus(test_id, status);

        return list.stream()
                .map(entity -> {
                    TestListeningDTO dto = convertToDTO(entity);
                    dto.setQuestions(testListeningQuestionService.findAllByTestListening_IdAndStatus(entity.getId(),status));
                    return dto;
                })
                .collect(Collectors.toList());
    }



    public int totalActiveListeningQuestionsByTestId(String testId) {
        testService.isExist(testId);

        List<TestListening> list = repository.findAllByTest_IdAndStatus(testId,StatusEnum.ACTIVE);

        if (list.isEmpty()) {
            return 0;
        }

        return list.stream()
                .mapToInt(listening -> {
                    List<TestListeningQuestion> questions = listening.getQuestions();
                    if (questions == null) {
                        return 0;
                    }

                    return (int) questions.stream()
                            .filter(question -> question.getStatus() == StatusEnum.ACTIVE)
                            .count();
                })
                .sum();
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
        TestListeningDTO dto = super.findById(id);
        try {
            firebaseStorageService.deleteFile(dto.getContent());

        } catch (IOException e) {
            log.error("Error occurred while deleting audio for TestListening with ID: {}", dto.getId(), e);
        }
        List<TestListeningQuestionDTO> questions = testListeningQuestionService.findAllByTestListening_Id(id);
        if (questions != null) {
            for (TestListeningQuestionDTO question : questions) {
                testListeningQuestionService.delete(question.getId());
            }
        }
        super.delete(id);
    }

}