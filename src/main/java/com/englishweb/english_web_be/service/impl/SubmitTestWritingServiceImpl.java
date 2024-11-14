package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SubmitTestWritingDTO;
import com.englishweb.english_web_be.model.SubmitTestWriting;
import com.englishweb.english_web_be.repository.SubmitTestWritingRepository;
import com.englishweb.english_web_be.service.SubmitTestWritingService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmitTestWritingServiceImpl extends BaseServiceImpl<SubmitTestWriting, SubmitTestWritingDTO, SubmitTestWritingRepository>
        implements SubmitTestWritingService {

    private final SubmitTestServiceImpl submitTestService;
    private final TestWritingServiceImpl testWritingService;

    public SubmitTestWritingServiceImpl(SubmitTestWritingRepository repository,
                                        @Lazy SubmitTestServiceImpl submitTestService,
                                        @Lazy TestWritingServiceImpl testWritingService) {
        super(repository);
        this.submitTestService = submitTestService;
        this.testWritingService = testWritingService;
    }


    public List<SubmitTestWritingDTO> findAllBySubmitTestId(String submitTestId) {
        List<SubmitTestWriting> entities = repository.findAllBySubmitTest_Id(submitTestId);
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @Override
    protected SubmitTestWriting convertToEntity(SubmitTestWritingDTO dto) {
        SubmitTestWriting entity = new SubmitTestWriting();
        entity.setId(dto.getId());
        entity.setSubmitTest(submitTestService.convertToEntity(submitTestService.findById(dto.getSubmitTestId())));
        entity.setTestWriting(testWritingService.convertToEntity(testWritingService.findById(dto.getTestWritingId())));
        entity.setScore(dto.getScore());
        entity.setContent(dto.getContent());
        entity.setComment(dto.getComment());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected SubmitTestWritingDTO convertToDTO(SubmitTestWriting entity) {
        SubmitTestWritingDTO dto = new SubmitTestWritingDTO();
        dto.setId(entity.getId());
        dto.setSubmitTestId(entity.getSubmitTest().getId());
        dto.setTestWritingId(entity.getTestWriting().getId());
        dto.setScore(entity.getScore());
        dto.setContent(entity.getContent());
        dto.setComment(entity.getComment());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
