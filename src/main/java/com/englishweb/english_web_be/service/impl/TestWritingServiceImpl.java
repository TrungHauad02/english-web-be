package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestWritingDTO;
import com.englishweb.english_web_be.dto.request.TestWritingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestWritingResponseDTO;
import com.englishweb.english_web_be.mapper.TestWritingMapper;
import com.englishweb.english_web_be.model.TestWriting;
import com.englishweb.english_web_be.repository.TestWritingRepository;
import com.englishweb.english_web_be.service.TestWritingService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestWritingServiceImpl extends BaseServiceImpl<TestWriting, TestWritingDTO, TestWritingRequestDTO, TestWritingResponseDTO, TestWritingMapper, TestWritingRepository> implements TestWritingService {

    private final TestServiceImpl testService;


    public TestWritingServiceImpl(TestWritingRepository repository,
                                  @Lazy TestServiceImpl testService,
                                  @Lazy TestWritingMapper mapper) {
        super(repository, mapper);
        this.testService = testService;
    }


    public List<TestWritingDTO> findAllDTOByTestId(String test_id) {
        testService.isExist(test_id);
        List<TestWriting> list = repository.findAllByTest_Id(test_id);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<TestWritingResponseDTO> findAllByTestId(String test_id) {
        testService.isExist(test_id);
        List<TestWriting> list = repository.findAllByTest_Id(test_id);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    protected TestWriting convertToEntity(TestWritingDTO dto) {
        TestWriting entity = new TestWriting();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setTest(testService.convertToEntity(testService.findDTOById(dto.getTestId())));
        return entity;
    }

    @Override
    protected TestWritingDTO convertToDTO(TestWriting entity) {

        TestWritingDTO dto = new TestWritingDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setTestId(entity.getTest().getId());
        return dto;
    }
}
