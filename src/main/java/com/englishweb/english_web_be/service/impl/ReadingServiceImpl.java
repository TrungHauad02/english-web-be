package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.ReadingRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingResponseDTO;
import com.englishweb.english_web_be.mapper.ReadingMapper;
import com.englishweb.english_web_be.model.Reading;
import com.englishweb.english_web_be.repository.ReadingRepository;
import com.englishweb.english_web_be.service.ReadingQuestionService;
import com.englishweb.english_web_be.service.ReadingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingServiceImpl extends BaseServiceImpl<Reading, ReadingDTO, ReadingRequestDTO,
        ReadingResponseDTO, ReadingMapper, ReadingRepository>
        implements ReadingService {

    private final ReadingQuestionService readingQuestionService;

    public ReadingServiceImpl(ReadingRepository repository, ReadingMapper mapper, ReadingQuestionService readingQuestionService) {
        super(repository, mapper);
        this.readingQuestionService = readingQuestionService;
    }

    @Override
    public void delete(String id) {
        List<ReadingQuestionDTO> dtoList = readingQuestionService.findAllDTOByReadingId(id);
        for(ReadingQuestionDTO dto: dtoList){
            readingQuestionService.delete(dto.getId());
        }
        super.delete(id);
    }

    @Override
    protected Reading convertToEntity(ReadingDTO dto) {
        Reading entity = new Reading();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setFile(dto.getFile());
        entity.setSerial(dto.getSerial());
        entity.setImage(dto.getImage());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected ReadingDTO convertToDTO(Reading entity) {
        ReadingDTO dto = new ReadingDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setFile(entity.getFile());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
