package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.model.TestListening;
import com.englishweb.english_web_be.model.TestListeningQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.TestListeningRepository;
import com.englishweb.english_web_be.service.TestListeningService;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


    public TestListeningServiceImpl(TestListeningRepository repository, @Lazy TestServiceImpl testService, @Lazy TestListeningQuestionServiceImpl testListeningQuestionService) {
        super(repository);
        this.testService = testService;
        this.testListeningQuestionService = testListeningQuestionService;
    }



    public int serialMaxListeningQuestionsByTestId(String testId) {


        testService.isExist(testId);


        List<TestListening> list = repository.findAllByTest_Id(testId);

        if (list.isEmpty()) {

            return 0;
        }


        list.sort(Comparator.comparingInt(TestListening::getSerial).reversed());
        TestListening listeningWithMaxSerial = list.get(0);

        List<TestListeningQuestion> questions = listeningWithMaxSerial.getQuestions();
        if (questions == null || questions.isEmpty()) {
            return 0;
        }

        questions.sort(Comparator.comparingInt(TestListeningQuestion::getSerial));
        TestListeningQuestion lastQuestion = questions.get(questions.size() - 1);

        return lastQuestion.getSerial();
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